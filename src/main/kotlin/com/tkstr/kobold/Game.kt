package com.tkstr.kobold

import com.tkstr.kobold.core.Engine

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