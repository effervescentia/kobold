package com.tkstr.kobold.utils

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import java.io.InputStream

/**
 * @author Ben Teichman
 */
class Packager {

    companion object Statics {

        val parser = Parser()

        fun parse(json: String): JsonObject {
            return parser.parse(json.byteInputStream()) as JsonObject
        }

        fun parse(stream: InputStream): JsonObject {
            return parser.parse(stream) as JsonObject
        }
    }
}