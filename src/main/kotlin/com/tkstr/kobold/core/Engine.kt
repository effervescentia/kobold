package com.tkstr.kobold.core

import com.beust.klaxon.JsonObject
import com.beust.klaxon.obj
import com.tkstr.kobold.core.Modules.Module
import com.tkstr.kobold.core.Modules.Property

/**
 * @author Ben Teichman
 */
class Engine : Module {
    override val key = "engine"

    val abilities: Map<String, Ability>
    val skills: Map<String, Skill>
    val sizes: Map<String, Size>
    val vision: Map<String, Vision>
    val movement: Map<String, Movement>
    val races: Map<String, Race>
    val classes: Map<String, Class>

    private var props = mapOf<String, Map<String, *>>()

    constructor(json: JsonObject) {
        // Order of these is very important
        abilities = process(json, "abilities", Ability.KEY) { key, json -> Ability(key, json, this) }
        skills = process(json, "skills", Skill.KEY) { key, json -> Skill(key, json, this) }
        sizes = processDefault(json, Size.DEFAULT, "sizes", Size.KEY) { key, json -> Size(key, json, this) }
        vision = processDefault(json, Vision.DEFAULT, "vision", Vision.KEY) { key, json -> Vision(key, json, this) }
        movement = processDefault(json, Movement.DEFAULT, "movement", Movement.KEY) { key, json -> Movement(key, json, this) }
        races = process(json, "races", Race.KEY) { key, json -> Race(key, json, this) }
        classes = process(json, "classes", Class.KEY) { key, json -> Class(key, json, this) }
    }

    private fun <T : Property> processDefault(json: JsonObject, default: JsonObject, key: String, propertyKey: String, factory: (key: String, json: JsonObject) -> T): Map<String, T> {
        if (json.containsKey(key)) return process(json.obj(key)!!, propertyKey, factory)
        else return process(default, propertyKey, factory)
    }

    private fun <T : Property> process(json: JsonObject, key: String, propertyKey: String, factory: (key: String, json: JsonObject) -> T): Map<String, T> {
        val modules = json.obj(key) ?: throw IllegalArgumentException("'$key' required for engine to function")
        return process(modules, propertyKey, factory)
    }

    private fun <T : Property> process(modules: JsonObject, propertyKey: String, factory: (key: String, json: JsonObject) -> T): Map<String, T> {
        val properties = modules.mapValues { entry -> factory(entry.key, entry.value as JsonObject) }
        props += propertyKey to properties
        return properties
    }

    fun <T : Property> properties(key: String): Map<String, T> {
        @Suppress("UNCHECKED_CAST")
        return props[key] as Map<String, T>? ?: throw IllegalStateException("'$key' do not exist in engine")
    }

}