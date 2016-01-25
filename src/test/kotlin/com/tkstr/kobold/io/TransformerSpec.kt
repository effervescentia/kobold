package com.tkstr.kobold.io

import com.beust.klaxon.json
import com.tkstr.kobold.io.types.LivingArray
import com.tkstr.kobold.io.types.LivingObject
import com.tkstr.kobold.io.types.LivingValue
import org.jetbrains.spek.api.Spek
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * @author Ben Teichman
 */
class TransformerSpec : Spek() { init {

    given("a simple json string") {

        val json = json {
            obj(
                    "unit" to 20
            ).toJsonString()
        }

        on("transformation") {

            val transformed = Transformer.from(json)

            it("should all be alive") {

                assertTrue { transformed is LivingObject }
                transformed as LivingObject
                assertEquals(1, transformed.size)
                assertTrue { transformed["unit"] is LivingValue<*> }
                val unit = transformed["unit"] as LivingValue<*>
                assertEquals(20, unit.originalAny)

            }

        }

    }

    given("a complex json string") {
        val json = json {
            obj(
                    "upper" to obj(
                            "middle" to array("inner", "inner 2", 29)
                    )
            ).toJsonString()
        }

        on("transformation") {

            val transformed = Transformer.from(json)

            it("should all be alive") {

                assertTrue { transformed is LivingObject }
                transformed as LivingObject
                assertEquals(1, transformed.size)
                assertTrue { transformed["upper"] is LivingObject }
                val upper = transformed["upper"] as LivingObject
                assertEquals(1, upper.size)
                assertTrue { upper["middle"] is LivingArray<*> }
                val middle = upper["middle"] as LivingArray<*>
                assertEquals(3, middle.size)
                middle.forEach { assertTrue { it is LivingValue<*> } }
                assertEquals("inner", (middle[0] as LivingValue<*>).originalAny)
                assertEquals("inner 2", (middle[1] as LivingValue<*>).originalAny)
                assertEquals(29, (middle[2] as LivingValue<*>).originalAny)

            }

        }
    }

}
}