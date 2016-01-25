package com.tkstr.kobold.io.types

import com.beust.klaxon.JsonArray

/**
 * @author Ben Teichman
 */
data class LivingArray<T>(val list: MutableList<T>, override val originalAny: JsonArray<*>) : LivingAny, MutableList<T> {

    override val size = list.size
    override fun add(element: T) = list.add(element)
    override fun add(index: Int, element: T) = list.add(index, element)
    override fun addAll(elements: Collection<T>) = list.addAll(elements)
    override fun addAll(index: Int, elements: Collection<T>) = list.addAll(index, elements)
    override fun clear() = list.clear()
    override fun remove(element: T) = list.remove(element)
    override fun removeAll(elements: Collection<T>) = list.removeAll(elements)
    override fun removeAt(index: Int) = list.removeAt(index)
    override fun retainAll(elements: Collection<T>) = list.retainAll(elements)
    override fun set(index: Int, element: T) = list.set(index, element)
    override fun subList(fromIndex: Int, toIndex: Int) = list.subList(fromIndex, toIndex)
    override fun contains(element: T) = list.contains(element)
    override fun containsAll(elements: Collection<T>) = list.containsAll(elements)
    override fun get(index: Int) = list[index]
    override fun indexOf(element: T) = list.indexOf(element)
    override fun isEmpty() = list.isEmpty()
    override fun lastIndexOf(element: T) = list.lastIndexOf(element)

    override fun listIterator() = list.listIterator()
    override fun listIterator(index: Int) = list.listIterator(index)
    override fun iterator() = list.iterator()


}