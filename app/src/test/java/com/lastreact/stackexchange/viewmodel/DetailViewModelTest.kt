package com.lastreact.stackexchange.viewmodel

import com.lastreact.entity.data.module.response.UserItem
import com.lastreact.repository.Repository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class DetailViewModelTest {

    @MockK
    lateinit var repository: Repository

    private lateinit var SUT: DetailViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        SUT = DetailViewModel(repository)
        Dispatchers.setMain(TestCoroutineDispatcher())
    }

    @Test
    fun `test on repository getUserById is exactly called once`() = runBlockingTest {
        coEvery { repository.getUserById(any()) } returns UserItem(userId = 0)

        SUT.retrieveUserById(0)

        coVerify(exactly = 1) { repository.getUserById(0) }

    }

}