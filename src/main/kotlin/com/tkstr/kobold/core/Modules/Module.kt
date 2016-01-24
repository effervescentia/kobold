package com.tkstr.kobold.core.Modules

import com.beust.klaxon.JsonObject
import com.beust.klaxon.string

/**
 * @author Ben Teichman
 */
interface Module {

    val key: String

    fun string(json: JsonObject, property: String): String {
        return json.string(property) ?: throw IllegalArgumentException("property '$property' is required")
    }

}