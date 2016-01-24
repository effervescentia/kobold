package com.tkstr.kobold.utils

import java.util.logging.Logger

/**
 * @author Ben Teichman
 */
fun <R : Any> R.logger(): Lazy<Logger> {
    return lazy { Logger.getLogger(this.javaClass.simpleName) }
}