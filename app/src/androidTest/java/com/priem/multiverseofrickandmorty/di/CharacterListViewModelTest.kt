package com.priem.multiverseofrickandmorty.di

import dagger.hilt.android.testing.HiltAndroidTest
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.priem.multiverseofrickandmorty.models.characterlist.CharacterList
import com.priem.multiverseofrickandmorty.repository.CharacterListRepository
import com.priem.multiverseofrickandmorty.repository.Response
import com.priem.multiverseofrickandmorty.viewmodels.CharacterListViewModel
import dagger.hilt.android.testing.CustomTestApplication
import dagger.hilt.android.testing.HiltAndroidRule
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.advanceUntilIdle
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class CharacterListViewModelTest {

    // Use HiltAndroidRule to initialize Hilt components
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    // Use InstantTaskExecutorRule for LiveData testing
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    // Mock CharacterListRepository
    @Mock
    lateinit var mockRepository: CharacterListRepository

    // Inject your ViewModel with the test module
    @Inject
    lateinit var viewModel: CharacterListViewModel

    // TestCoroutineDispatcher for testing coroutines
    private val testDispatcher = TestCoroutineDispatcher()

    // TestCoroutineScope for managing coroutines in tests
    private val testScope = TestCoroutineScope(testDispatcher)

    @Before
    fun setup() {
        hiltRule.inject()

        MockitoAnnotations.openMocks(this)
        // Inject the mock repository into the ViewModel
        viewModel = CharacterListViewModel(mockRepository)

    }

    @Test
    fun testCharacterListViewModel() {
        // You can write your ViewModel tests here using the injected ViewModel instance.
        // For example, you can observe LiveData and assert the expected results.

        // Sample CharacterList data for testing
        val testData = TestData.characterList

        // Mock a successful response from the repository
        `when`(mockRepository.characterList).thenReturn(
            MutableLiveData(Response.Success(testData))
        )

        // Create an Observer to observe the LiveData from the ViewModel
        val observer = Observer<Response<CharacterList>> { response ->
            if (response is Response.Success) {
                // Verify that the data in the response matches the sample data
                val characterList = response.data
                assert(characterList?.results?.size == testData.results.size)

                // Add more assertions as needed to validate the data
            }
            else if (response is Response.Error) {
                // Handle error case if needed
            }
        }

        // Observe the LiveData from the ViewModel
        viewModel.characterList.observeForever(observer)

        // Ensure that LiveData updates are processed
        testScope.advanceUntilIdle()

        // Remove the observer to prevent memory leaks
        viewModel.characterList.removeObserver(observer)
    }
}
