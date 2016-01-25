package com.tkstr.kobold.core

import com.beust.klaxon.JsonObject
import com.tkstr.kobold.utils.Packager
import com.tkstr.kobold.core.Modules.ModuleCompanion
import com.tkstr.kobold.core.Modules.Property

/**
 * @author Ben Teichman
 */
class Size(key: String, json: JsonObject, engine: Engine) : Property(key, json, engine) {

    companion object : ModuleCompanion {
        override val KEY = "size"
        override val DEFAULT = Packager.parse(Packager::class.java.getResourceAsStream("/_engine/_size.json"))
    }

}