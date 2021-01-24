package com.lastreact.stackexchange.extensions

import org.junit.Assert.*
import org.junit.Test

class LongExtKtTest {

    @Test
    fun `correct date is parsed from LONG number`() {
        val date = 1326311637L.convertLongToTime()

        assertTrue(date == "11-01-2012")
    }

    @Test
    fun `correct date is parsed from LONG number on 0L value`() {
        val date = 0L.convertLongToTime()

        assertTrue(date == "01-01-1970")
    }
}