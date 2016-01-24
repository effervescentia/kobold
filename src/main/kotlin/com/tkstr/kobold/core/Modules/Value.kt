package com.tkstr.kobold.core.Modules

import com.beust.klaxon.JsonObject
import com.beust.klaxon.boolean
import com.tkstr.kobold.core.Engine

/**
 * @author Ben Teichman
 */
open class Value(override val key: String, json: JsonObject, engine: Engine) : Submodule(json, engine), Processable {

    var enabled = false

    override fun process(): Value {
        enabled = json.boolean("enabled") ?: true
        return this
    }

}