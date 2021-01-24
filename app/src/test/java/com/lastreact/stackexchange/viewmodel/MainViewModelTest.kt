package com.lastreact.stackexchange.viewmodel

import com.lastreact.entity.data.module.response.UserItem
import com.lastreact.repository.Repository
import com.lastreact.service.di.ResponseHandler
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @MockK
    lateinit var repository: Repository

    @MockK
    lateinit var responseHandler: ResponseHandler

    private lateinit var SUT: MainViewModel

    private val mockListData = arrayListOf(
        UserItem(userId = 23, displayName = "David"),
        UserItem(userId = 24, displayName = "Daniel"),
        UserItem(userId = 25, displayName = "Susan"),
    )

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        SUT = MainViewModel(repository)
        Dispatchers.setMain(TestCoroutineDispatcher())
    }

    @Test
    fun `test onViewModel getUsers called repository getUsers is invoked`() = runBlockingTest {

        coEvery { repository.getUsers(responseHandler) } returns Unit

        SUT.getUsers(responseHandler)

        coVerify(exactly = 1) { repository.getUsers(responseHandler) }
    }

    @Test
    fun `test onViewModel getUsers called repository getUsersList is invoked`() = runBlockingTest {

        coEvery { repository.getUsersList() } returns mockListData

        SUT.getUsers()

        coVerify(exactly = 1) { repository.getUsersList() }
    }

    @Test
    fun `test getFilteredItems filter by name`() = runBlockingTest {
        SUT.userItemsFiltered.clear()
        SUT.userItems = mockListData
        val result = SUT.getFilteredItems("Daniel")

        assertTrue(result.size == 1)
    }
}