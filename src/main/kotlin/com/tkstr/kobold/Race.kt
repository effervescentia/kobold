package com.tkstr.kobold

/**
 * Race
 *
 * @author Ben Teichman
 */
interface Race: Named {
    val nameSuggestions: Map<String, NameSuggestion>
    val namePatterns: Array<Any>
    val ability: Map<String, Int>
    val age: Map<String, Int>
    val build: Build
    val movement: Map<String, Int>
    val vision: Map<String, Int>
    val language: Array<String>
}