package com.tkstr.kobold

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import org.jetbrains.spek.api.Spek
import kotlin.test.assertEquals

/**
 * @author Ben Teichman
 */
class GameSpec : Spek() { init {

    given("a game") {

        val engineJson = Parser().parse(javaClass.getResourceAsStream("/miniEngine.json")) as JsonObject

        val game = Game.from(engineJson.toJsonString())

        on("abilities requested") {
            val abilities = game.engine.abilities

            it("should have 2 abilities") {
                assertEquals(2, abilities.size)
            }

            it("should have required properties") {
                val dexterity = abilities["DEX"]!!
                assertEquals("DEX", dexterity.key)
                assertEquals("Dexterity", dexterity.name)

                val intelligence = abilities["INT"]!!
                assertEquals("INT", intelligence.key)
                assertEquals("Intelligence", intelligence.name)
            }
        }

        on("skills requested") {
            val skills = game.engine.skills

            it("should have 2 skills") {
                assertEquals(2, skills.size)
            }

            it("should have required properties") {
                val acrobatics = skills["acrobatics"]!!
                assertEquals("acrobatics", acrobatics.key)
                assertEquals("Acrobatics", acrobatics.name)
                assertEquals("DEX", acrobatics.ability.key)
                assertEquals("Dexterity", acrobatics.ability.name)

                val history = skills["history"]!!
                assertEquals("history", history.key)
                assertEquals("History", history.name)
                assertEquals("INT", history.ability.key)
                assertEquals("Intelligence", history.ability.name)
            }
        }

        on("sizes requested") {
            val sizes = game.engine.sizes

            it("should have 3 sizes") {
                assertEquals(3, sizes.size)
            }

            it("should have required properties") {
                val small = sizes["small"]!!
                assertEquals("small", small.key)
                assertEquals("Small", small.name)

                val medium = sizes["medium"]!!
                assertEquals("medium", medium.key)
                assertEquals("Medium", medium.name)
            }
        }

        on("races requested") {
            val races = game.engine.races

            it("should have 2 races") {
                assertEquals(2, races.size)
            }

            it("should have required properties") {
                val imp = races["imp"]!!
                assertEquals("imp", imp.key)
                assertEquals("Imp", imp.name)
                assertEquals("medium", imp.size.key)
                assertEquals("Medium", imp.size.name)
                assertEquals(2, imp.movement.size)
                val impWalk = imp.movement["walk"]!!
                assertEquals("walk", impWalk.key)
                assertEquals(30, impWalk.speed)
                val impFly = imp.movement["fly"]!!
                assertEquals("fly", impFly.key)
                assertEquals(20, impFly.speed)
                assertEquals(1, imp.vision.size)
                println(imp.vision)
                val impVision = imp.vision["darkvision"]!!
                assertEquals(true, impVision.enabled)
                assertEquals(60, impVision.distance)

                val centaur = races["centaur"]!!
                assertEquals("centaur", centaur.key)
                assertEquals("Centaur", centaur.name)
                assertEquals("large", centaur.size.key)
                assertEquals("Large", centaur.size.name)
                assertEquals(1, centaur.movement.size)
                val centaurWalk = centaur.movement["walk"]!!
                assertEquals("walk", centaurWalk.key)
                assertEquals(40, centaurWalk.speed)
                assertEquals(1, centaur.vision.size)
                val centaurVision = centaur.vision["normal"]!!
                assertEquals("normal", centaurVision.key)
                assertEquals(true, centaurVision.enabled)
            }
        }

        on("classes requested") {
            val classes = game.engine.classes

            it("should have 2 classes") {
                assertEquals(2, classes.size)
            }

            it("should have required properties") {
                val witchDoctor = classes["witch_doctor"]!!
                assertEquals("witch_doctor", witchDoctor.key)
                assertEquals("Witch Doctor", witchDoctor.name)

                val singsword = classes["singsword"]!!
                assertEquals("singsword", singsword.key)
                assertEquals("Singsword", singsword.name)
            }
        }
    }

}
}