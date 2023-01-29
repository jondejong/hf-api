package com.jondejong.hf.api.unit

import com.jondejong.hf.api.game.Shoe
import org.junit.Test
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ShoeTest {

    @Test
    fun `can create a shoe`() {
        val shoe = Shoe(UUID.randomUUID().toString(), 6)
        assertEquals(6 * 54, shoe.cards.size)
        var previousPosition = -1
        shoe.cards.forEach {
            assertTrue(it.position > previousPosition)
            previousPosition = it.position
        }
    }
}