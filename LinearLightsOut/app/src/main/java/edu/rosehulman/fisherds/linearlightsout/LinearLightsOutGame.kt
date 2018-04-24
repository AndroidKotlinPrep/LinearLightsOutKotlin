package edu.rosehulman.fisherds.linearlightsout

import java.util.*
import kotlin.math.max

class LinearLightsOutGame {
    var lights: BooleanArray
    var numPresses: Int

    constructor(numLights: Int, randomize: Boolean = true) {
        lights = BooleanArray(max(numLights, 5))
        if (randomize) {
            val random = Random();
            for (k in 0..100) {
                pressButtonAt(random.nextInt(lights.size))
            }
        }
        numPresses = 0
    }

    fun isLightOnAt(index: Int): Boolean {
        return lights[index]
    }

    fun pressButtonAt(index: Int): Boolean {
        if (index < 0 || index >= lights.size) {
            return isGameWon()
        }
        numPresses += 1
        lights[index] = !lights[index]
        if (index > 0) {
            lights[index - 1] = !lights[index - 1]
        }
        if (index < lights.size - 1) {
            lights[index + 1] = !lights[index + 1]
        }
        return isGameWon()
    }

    fun isGameWon(): Boolean {
        return !(lights.contains(true) && lights.contains(false))
    }

    override fun toString(): String {
        var gameStr = ""
        for (light in lights) {
            gameStr += if (light) "1" else "0"
        }
        return gameStr
        //return Arrays.toString(lights)
    }
}