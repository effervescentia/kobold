package com.tkstr.kobold

import com.tkstr.kobold.core.Engine
import com.tkstr.kobold.utils.Packager

/**
 * @author Ben Teichman
 */
class Game private constructor(engine: Engine) {

    val engine = engine

    companion object Factory {

        fun from(json: String): Game {
            return Game(Engine(Packager.parse(json)))
        }

    }

}