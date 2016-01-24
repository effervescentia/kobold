package com.tkstr.kobold.core

import com.beust.klaxon.JsonObject
import com.beust.klaxon.int
import com.beust.klaxon.json
import com.tkstr.kobold.Packager
import com.tkstr.kobold.core.Modules.ModuleCompanion
import com.tkstr.kobold.core.Modules.Property

/**
 * @author Ben Teichman
 */
class Vision(key: String, json: JsonObject, engine: Engine) : Property(key, json, engine) {

    companion object : ModuleCompanion {
        override val KEY = "vision"

        override val DEFAULT = Packager.parse(Packager::class.java.getResourceAsStream("/_engine/_vision.json"))
    }

    public class Value(key: String, json: JsonObject, engine: Engine) : com.tkstr.kobold.core.Modules.Value(key, json, engine) {
        var distance = json.int("distance") ?: NO_DISTANCE

        companion object : ModuleCompanion {
            override val KEY = Vision.KEY
            // TODO externalize this to JSON file
            override val DEFAULT = json {
                obj(
                        Vision.KEY to obj(
                                "normal" to obj(
                                        "enabled" to true
                                )
                        )
                )
            }

            const val NO_DISTANCE = -1
        }

        override fun process(): Value {
            enabled = if (distance === NO_DISTANCE) false else enabled
            return super.process() as Value
        }

    }

}