package com.tkstr.kobold.io.types

import com.beust.klaxon.JsonObject

/**
 * @author Ben Teichman
 */
data class LivingObject(val map: MutableMap<String, Any>, override val originalAny: JsonObject) : LivingAny, MutableMap<String, Any> {

    override val size = map.size
    override val entries = map.entries
    override val keys = map.keys
    override val values = map.values
    override fun clear() = map.clear()
    override fun put(key: String, value: Any) = map.put(key, value)
    override fun putAll(from: Map<out String, Any>) = map.putAll(from)
    override fun remove(key: String) = map.remove(key)
    override fun containsKey(key: String) = map.containsKey(key)
    override fun containsValue(value: Any) = map.containsValue(value)
    override fun get(key: String) = map[key]
    override fun isEmpty() = map.isEmpty()
}