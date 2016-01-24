package com.tkstr.kobold.core.Modules

import com.beust.klaxon.JsonObject
import com.tkstr.kobold.core.Engine

/**
 * @author Ben Teichman
 */
abstract class Property(override val key: String, json: JsonObject, engine: Engine) : Named(json, engine), Processable {
}