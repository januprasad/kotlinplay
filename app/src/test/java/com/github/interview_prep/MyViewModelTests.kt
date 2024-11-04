package com.github.kotlin_tryout

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.viewModelScope
import app.cash.turbine.test
import io.ktor.client.HttpClient
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MyViewModelTests {
    private lateinit var myViewModel: MyViewModel
    val dispatcherProvider = TestDispatcherProvider()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @RelaxedMockK
    lateinit var client: HttpClient

    @RelaxedMockK
    lateinit var mobula: Mobula

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun init() {
        MockKAnnotations.init(this)
        myViewModel = MyViewModel()
        client = HttpClient()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test`() {
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
//        val mobula = Mobula(client)
//        val responseObject =Response(
//            data = Crypto(
//                name = "Bitcoin",
//                price = 100000.0,
//                symbol = "BTC"
//            ))

//        runTest {
//            coEvery {
//                val res = mobula.getMethod("Abc")
//                res.await()
//            } returns "Abc"
//
//            myViewModel.fetchData("Abc")
//            myViewModel.uiState.test {
//                assertEquals(
//                    AppState.Loaded("Abc"),
//                    awaitItem(),
//                )
//                cancelAndIgnoreRemainingEvents()
//            }
//
// //            coEvery {
// //                mobula.parse("Abc").await()
// //            } returns responseObject
//
// //            coEvery {
// //                responseObject.data
// //            } returns cryptoReponse
// //
//
//
// //            coVerify(exactly = 2) {
// //                mobula.test("")
// //            }
//

//        }
    }

    @After
    fun tearDown() {
        runTest {
            myViewModel.viewModelScope.cancel()
        }
    }
}
