package com.github.interview_prep

import androidx.lifecycle.viewModelScope
import app.cash.turbine.test
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MyViewModelTests {

    private lateinit var myViewModel: MyViewModel
    val dispatcherProvider = TestDispatcherProvider()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun init() {

//        myViewModel = MyViewModel(dispatcherProvider)
        myViewModel = MyViewModel()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test`() {
        val mobula = mockk<Mobula>()
        runTest {
            coEvery {
               mobula.test("").await()
            } returns "Abcd"

            myViewModel.test("Abcd")
//            coVerify(exactly = 1) {
//                mobula.test("").await()
//            }

            myViewModel.uiState.test {
                assertEquals(
                    AppState.Loaded("Abcd"),
                    awaitItem(),
                )
                cancelAndIgnoreRemainingEvents()
            }

        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `testLoaded`() {
        val mobula = mockk<Mobula>()
        val responseObject =Response(
            data = Crypto(
                name = "Bitcoin",
                price = 100000.0,
                symbol = "BTC"
            ))

        val response = {}

        runTest {
            coEvery {
                val res = mobula.getPrice("Abc")
                res.await()
            } returns "Abc"

//            coEvery {
//                mobula.parse("Abc").await()
//            } returns responseObject

//            coEvery {
//                responseObject.data
//            } returns cryptoReponse
//

            myViewModel.fetchData("Abc")
//            coVerify(exactly = 2) {
//                mobula.test("")
//            }

            myViewModel.uiState.test {
                assertEquals(
                    AppState.Loaded("Abc"),
                    awaitItem(),
                )
                cancelAndIgnoreRemainingEvents()
            }
        }
    }


    @After
    fun tearDown() {
        runTest {
            myViewModel.viewModelScope.cancel()
        }
    }

}