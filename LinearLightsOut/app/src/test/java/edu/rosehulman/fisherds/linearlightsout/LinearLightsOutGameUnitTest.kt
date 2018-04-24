package edu.rosehulman.fisherds.linearlightsout

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class LinearLightsOutGameUnitTest {

    lateinit var game: LinearLightsOutGame

    @Before
    fun setup() {
        game = LinearLightsOutGame(7, randomize = false)
    }

    @Test
    fun constructorMakesAllFalse() {
        assertEquals("0000000", game.toString())
    }

    @Test
    fun pressStart() {
        game.pressButtonAt(0)
        assertEquals("1100000", game.toString())
    }

    @Test
    fun pressMiddle() {
        game.pressButtonAt(3)
        assertEquals("0011100", game.toString())
        game.pressButtonAt(3)
        assertEquals("0000000", game.toString())
        game.pressButtonAt(3)
        assertEquals("0011100", game.toString())
    }

    @Test
    fun pressEnd() {
        game.pressButtonAt(6)
        assertEquals("0000011", game.toString())
    }

    @Test
    fun pressInvalids() {
        game.pressButtonAt(-1)
        assertEquals("0000000", game.toString())
        game.pressButtonAt(7)
        assertEquals("0000000", game.toString())
    }

    @Test
    fun initialWin() {
        assertEquals(true, game.isGameWon())

        assertFalse(game.pressButtonAt(0))
        assertTrue(game.pressButtonAt(0))
    }

    @Test
    fun win() {
        assertFalse(game.pressButtonAt(0))
        assertEquals("1100000", game.toString())
        assertFalse(game.pressButtonAt(3))
        assertEquals("1111100", game.toString())
        assertTrue(game.pressButtonAt(6))
        assertTrue(game.isGameWon())
    }

    @Test
    fun getLight() {
        assertFalse(game.isLightOnAt(0))
        assertFalse(game.pressButtonAt(3))
        assertFalse(game.isLightOnAt(0))
        assertFalse(game.isLightOnAt(1))
        assertTrue(game.isLightOnAt(2))
        assertTrue(game.isLightOnAt(3))
        assertTrue(game.isLightOnAt(4))
        assertFalse(game.isLightOnAt(5))
        assertFalse(game.isLightOnAt(6))
    }

    @Test
    fun getLightInvalid() {
        assertFalse(game.isLightOnAt(-1))
        assertFalse(game.isLightOnAt(6))
    }
}
