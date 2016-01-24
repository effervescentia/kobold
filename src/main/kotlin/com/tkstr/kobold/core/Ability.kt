package com.tkstr.kobold.core

import com.beust.klaxon.JsonObject
import com.tkstr.kobold.core.Modules.ModuleCompanion
import com.tkstr.kobold.core.Modules.Property

/**
 * @author Ben Teichman
 */
class Ability(key: String, json: JsonObject, engine: Engine) : Property(key, json, engine) {

    companion object : ModuleCompanion {
        override val DEFAULT = null
        override val KEY = "ability"
    }
}