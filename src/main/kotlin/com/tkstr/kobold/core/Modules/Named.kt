package com.tkstr.kobold.core.Modules

import com.beust.klaxon.JsonObject
import com.tkstr.kobold.core.Engine

/**
 * @author Ben Teichman
 */
abstract class Named(json: JsonObject, engine: Engine) : Submodule(json, engine) {

    val name = string(json, "name")

}