package com.tkstr.kobold.core.Modules

import com.beust.klaxon.JsonObject
import com.beust.klaxon.obj
import com.beust.klaxon.string
import com.tkstr.kobold.core.Engine
import com.tkstr.kobold.utils.logger

/**
 * @author Ben Teichman
 */
abstract class Submodule : Module {
    val engine: Engine
    val json: JsonObject
    protected val LOG by logger()

    constructor(json: JsonObject, engine: Engine) {
        this.json = json
        this.engine = engine
    }

    class FieldException(field: String, json: JsonObject) : Exception("field '$field' was not found in JSON: ${json.toJsonString()}")
    class PropertyException(property: String, companion: ModuleCompanion, engine: Engine) : Exception("property '$property' was not found in loaded '${companion.KEY}': ${engine.properties<Property>(companion.KEY).keys}")

    protected fun property(json: JsonObject, property: String, companion: ModuleCompanion): Property {
        val jsonProperty = json.string(property) ?: throw FieldException(property, json)
        return findProperty(engine, companion, jsonProperty)
    }

    private fun <T : Property> findProperty(engine: Engine, companion: ModuleCompanion, key: String): T {
        return engine.properties<T>(companion.KEY)[key] ?: throw PropertyException(key, companion, engine)
    }

    protected fun mapProperties(json: JsonObject, property: String, companion: ModuleCompanion): Map<String, Property> {
        return map(json, property) { jsonMap, key -> property(jsonMap, key, companion) }
    }

    protected fun value(json: JsonObject, property: String): Value {
        val jsonValue = json[property] ?: throw FieldException(property, json)
        return Value(property, jsonValue as JsonObject, engine)
    }

    protected fun mapValues(json: JsonObject, property: String): Map<String, Value> {
        return map(json, property) { jsonMap, key -> value(jsonMap, key) }
    }

    protected fun <T : Property, V : Value> mapKeyedValues(json: JsonObject, default: JsonObject, property: String, companion: ModuleCompanion, factory: (jsonMap: JsonObject, key: String) -> V): Map<String, V> {
        try {
            return mapKeyedValues<T, V>(json, property, companion, factory)
        } catch(e: Exception) {
            LOG.warning(e.message)
            return mapKeyedValues<T, V>(default, property, companion, factory)
        }
    }

    @Suppress("UNCHECKED_CAST")
    protected fun <T : Property, V : Value> mapKeyedValues(json: JsonObject, property: String, companion: ModuleCompanion, factory: (jsonMap: JsonObject, key: String) -> V): Map<String, V> {
        val jsonMap = json.obj(property) ?: throw FieldException(property, json)
        return jsonMap.mapKeys { entry -> findProperty<T>(engine, companion, entry.key).key }.mapValues { entry -> factory(entry.value as JsonObject, entry.key).process() as V }
    }

    private fun <T : Module> map(json: JsonObject, property: String, factory: (jsonMap: JsonObject, key: String) -> T): Map<String, T> {
        val jsonMap = json.obj(property) ?: throw FieldException(property, json)
        return jsonMap.mapValues { entry -> factory(jsonMap, entry.key) }
    }
}