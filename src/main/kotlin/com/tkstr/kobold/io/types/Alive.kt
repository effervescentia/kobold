package com.tkstr.kobold.io.types

/**
 * @author Ben Teichman
 */
interface Alive {

    open fun configure() = Unit
    open fun activate() = Unit
    open fun deactivate() = Unit
    open fun destroy() = Unit

}