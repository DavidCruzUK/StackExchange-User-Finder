package com.lastreact.repository.data.mapper

import com.lastreact.entity.data.module.response.StackApiResponse
import com.lastreact.entity.data.module.response.UserItem
import com.lastreact.repository.db.User
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Test

class DataMappersKtTest {

    @Test
    fun `test List UserItem correct mapped into List Room User Entity`() = runBlocking {

        val response = StackApiResponse(listOf(UserItem(userId = 101), UserItem(userId = 102)))

        val user = response.mapToUserDao()

        assertTrue(user[0].userId == 101 && user[1].userId == 102)
    }

    @Test
    fun `test List User Room correct mapped into List UserItem`() = runBlocking {

        val response = listOf(User(101), User(102))

        val user = response.mapToPresent()

        assertTrue(user[0].userId == 101 && user[1].userId == 102)
    }

    @Test
    fun `test User Room correct mapped into UserItem`() = runBlocking {

        val response = User(101)

        val user = response.mapToPresent()

        assertTrue(user.userId == 101)
    }


}