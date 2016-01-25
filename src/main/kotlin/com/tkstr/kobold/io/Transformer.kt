package com.tkstr.kobold.io

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.tkstr.kobold.io.types.LivingArray
import com.tkstr.kobold.io.types.LivingObject
import com.tkstr.kobold.io.types.LivingValue
import com.tkstr.kobold.utils.logger
import java.io.InputStream
import java.util.*

/**
 * @author Ben Teichman
 */
object Transformer {

    val LOG by logger()

    fun from(json: String) = from(json.byteInputStream())
    fun from(jsonStream: InputStream) = from(Parser().parse(jsonStream) as JsonObject)
    fun from(json: JsonObject): Any {
        try {
            return transform(json)
        } finally{
            LOG.info { "it's alive!" }
        }
    }

    private fun transform(any: Any): Any {
        return when (any) {
            is JsonObject -> transformObj(any)
            is JsonArray<*> -> transformArr(any)
            else -> LivingValue(any)
        }
    }

    private fun transformObj(json: JsonObject) = LivingObject(HashMap(json.mapValues { transform(it.value!!) }), json)

    private fun transformArr(json: JsonArray<*>) = LivingArray(ArrayList(json.map { transform(it!!) }), json)

}