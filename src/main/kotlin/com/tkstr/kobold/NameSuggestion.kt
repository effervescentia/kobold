package com.tkstr.kobold

/**
 * NameSuggestion
 *
 * @author Ben Teichman
 */
interface NameSuggestion: Named {
    val options: Array<String>
}