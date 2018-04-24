package edu.rosehulman.fisherds.linearlightsout

import java.util.*

class LinearLightsOutGame {

    var mLights: BooleanArray

    constructor(numLights: Int) {
        val random = Random();
        mLights = BooleanArray(numLights)


        for (k in 0..100) {
            pressButtonAt(random.nextInt(numLights))
        }
    }

    fun pressButtonAt(index: Int): Boolean {
        mLights[index] = !mLights[index]
        if (index > 0) {
            mLights[index - 1] = !mLights[index - 1]
        }
        if (index < mLights.size - 1) {
            mLights[index + 1] = !mLights[index + 1]
        }
        return false
    }

    override fun toString(): String {
        var gameStr = ""
        for (light in mLights) {
            gameStr += if (light) "1" else "0"
        }
        return gameStr
        //return Arrays.toString(mLights)
    }
}