package com.giftech.githubku

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import com.giftech.githubku.data.MainRepository
import com.giftech.githubku.data.model.User
import com.giftech.githubku.ui.home.HomeViewModel
import com.giftech.githubku.utils.Resource
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import kotlin.math.exp


@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: MainRepository

    @Mock
    private lateinit var viewModel: HomeViewModel

    @Test
    fun getUser(){
        val dummyData = Resource.Success(Dummy.dummyUser())
        val expectedUsers = MutableLiveData<Resource<List<User>>>()
        expectedUsers.value = dummyData

        `when`(viewModel.users).thenReturn(expectedUsers)

        val actualUsers = viewModel.users.getOrAwaitValue()

        if (actualUsers is Resource.Success){
            verify(viewModel).users
            assertNotNull(actualUsers.data)
            assertEquals(dummyData.data.size, actualUsers.data.size)
        }
    }

}