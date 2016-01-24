package com.tkstr.kobold.core

import com.beust.klaxon.JsonObject
import com.beust.klaxon.int
import com.tkstr.kobold.Packager
import com.tkstr.kobold.core.Modules.ModuleCompanion
import com.tkstr.kobold.core.Modules.Property

/**
 * @author Ben Teichman
 */
class Movement(key: String, json: JsonObject, engine: Engine) : Property(key, json, engine) {

    companion object : ModuleCompanion {
        override val KEY = "movement"
        override val DEFAULT = Packager.parse(Packager::class.java.getResourceAsStream("/_engine/_movement.json"))
    }

    public class Value(key: String, json: JsonObject, engine: Engine) : com.tkstr.kobold.core.Modules.Value(key, json, engine) {

        var speed = NO_SPEED

        companion object : ModuleCompanion {
            override val DEFAULT = null
            override val KEY = Movement.KEY

            const val NO_SPEED = -1
        }

        override fun process(): Value {
            speed = json.int("speed") ?: NO_SPEED
            return super.process() as Value
        }

    }

}