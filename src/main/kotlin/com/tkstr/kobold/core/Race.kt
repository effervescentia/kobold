package com.tkstr.kobold.core

import com.beust.klaxon.JsonObject
import com.tkstr.kobold.core.Modules.ModuleCompanion
import com.tkstr.kobold.core.Modules.Property

/**
 * @author Ben Teichman
 */
class Race(key: String, json: JsonObject, engine: Engine) : Property(key, json, engine) {

    companion object : ModuleCompanion {
        override val DEFAULT = null
        override val KEY = "race"
    }

    val size = property(json, "size", Size.Companion)
    val movement = mapKeyedValues<Movement, Movement.Value>(json, "movement", Movement.Companion) { jsonMap, key -> Movement.Value(key, jsonMap, engine) }
    val vision = mapKeyedValues<Vision, Vision.Value>(json, Vision.Value.DEFAULT, "vision", Vision.Companion) { jsonMap, key -> Vision.Value(key, jsonMap, engine) }
}