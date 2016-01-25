package com.tkstr.kobold

import com.tkstr.kobold.utils.logger
import org.jetbrains.spek.api.Spek

/**
 * @author Ben Teichman
 */
class ConstructorSpec : Spek() {


    open class Innermost {

        val LOG by logger()

        constructor() {
            LOG.info("constructed")
        }
    }

    class MiddlemostByConvention : Innermost {

        constructor() : super() {
            LOG.info("constructed")
        }
    }

    class MiddlemostByLegacy : Innermost {
        constructor() {
            LOG.info("constructed")
        }
    }

    init {

        given("a class") {

            on("instantiation") {
                it("should log") {
                    val inner = MiddlemostByLegacy()
                }
            }

        }

    }
}