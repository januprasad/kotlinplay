package com.github.interview_prep

import android.util.Log
import androidx.lifecycle.viewModelScope
import app.cash.turbine.test
import com.github.interview_prep.mockkk.UiDataModel
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MyViewModelTests {

    private lateinit var myViewModel: MyViewModel
    val dispatcherProvider = TestDispatcherProvider()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun init() {

        myViewModel = MyViewModel(dispatcherProvider)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test`() {
        val mobula = mockk<Mobula>()
        runTest {
            coEvery {
               mobula.test("").await()
            } returns "Abc"

            myViewModel.test()
//            coVerify(exactly = 1) {
//                mobula.test("").await()
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

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `testLoaded`() {
        val mobula = mockk<Mobula>()
        val responseObject = mockk<Response>()
        val cryptoReponse = mockk<Crypto>()
        val response =
            "{\"data\":{\"market_cap\":1397006575547,\"market_cap_diluted\":1488766711831,\"liquidity\":288930016,\"price\":70893.65294431684,\"off_chain_volume\":20610111334,\"volume\":269556800,\"volume_change_24h\":-0.22692684829235077,\"volume_7d\":269556800,\"is_listed\":true,\"price_change_24h\":-0.2281124,\"price_change_1h\":-0.055411085,\"price_change_7d\":1.4587651,\"price_change_1m\":11.3171,\"price_change_1y\":160.50818,\"ath\":73426.18983181914,\"atl\":67.809,\"name\":\"Bitcoin\",\"symbol\":\"BTC\",\"logo\":\"https://metacore.mobula.io/ea67a92c8e0a9b951d6fafed56e8ee714180e9ccbadb7d0555b9cb1b1224dba7.png\",\"rank\":1,\"contracts\":[{\"address\":\"0x2260fac5e5542a773aa44fbcfedf7c193bc2c599\",\"blockchain\":\"Ethereum\",\"blockchainId\":\"1\",\"decimals\":8},{\"address\":\"0x7130d2a12b9bcbfae4f2634d864a1ee1ce3ead9c\",\"blockchain\":\"BNB Smart Chain (BEP20)\",\"blockchainId\":\"56\",\"decimals\":18},{\"address\":\"0x1bfd67037b42cf73acf2047067bd4f2c47d9bfd6\",\"blockchain\":\"Polygon\",\"blockchainId\":\"137\",\"decimals\":8},{\"address\":\"0x50b7545627a5162f82a992c33b87adc75187b218\",\"blockchain\":\"Avalanche C-Chain\",\"blockchainId\":\"43114\",\"decimals\":8},{\"address\":\"0x2f2a2543b76a4166549f7aab2e75bef0aefc5b0f\",\"blockchain\":\"Arbitrum\",\"blockchainId\":\"42161\",\"decimals\":8},{\"address\":\"0x6ab6d61428fde76768d7b45d8bfeec19c6ef91a8\",\"blockchain\":\"Moonriver\",\"blockchainId\":\"1285\",\"decimals\":8},{\"address\":\"0xd629eb00deced2a080b7ec630ef6ac117e614f1b\",\"blockchain\":\"Celo\",\"blockchainId\":\"42220\",\"decimals\":18},{\"address\":\"0xdc0486f8bf31df57a952bcd3c1d3e166e3d9ec8b\",\"blockchain\":\"Boba\",\"blockchainId\":\"288\",\"decimals\":8},{\"address\":\"0x062e66477faf219f25d27dced647bf57c3107d52\",\"blockchain\":\"Cronos\",\"blockchainId\":\"25\",\"decimals\":8},{\"address\":\"0xf4eb217ba2454613b15dbdea6e5f22276410e89e\",\"blockchain\":\"Aurora\",\"blockchainId\":\"1313161554\",\"decimals\":8},{\"address\":\"0x68f180fcce6836688e9084f035309e29bf0a2095\",\"blockchain\":\"Optimistic\",\"blockchainId\":\"10\",\"decimals\":8},{\"address\":\"0x8e5bbbb09ed1ebde8674cda39a0c169401db4252\",\"blockchain\":\"XDAI\",\"blockchainId\":\"100\",\"decimals\":8},{\"address\":\"0x922d641a426dcffaef11680e5358f34d97d112e1\",\"blockchain\":\"Moonbeam\",\"blockchainId\":\"1284\",\"decimals\":8},{\"address\":\"xUTp3RXGJ1fJpCGqsAY6GgyfRQ3WQ1MdcYR1SiwndAbR\",\"blockchain\":\"Alephium\",\"blockchainId\":\"alephium-0\",\"decimals\":8}],\"total_supply\":\"21000000\",\"circulating_supply\":\"19705665\"}}"

        runTest {
            coEvery {
                val res = mobula.getPrice(any(), dispatcherProvider)
                res.await()
            } returns response

            coEvery {
                mobula.parse(response).await()
            } returns responseObject

            coEvery {
                responseObject.data
            } returns cryptoReponse
//

            myViewModel.fetchData()
//            coVerify(exactly = 2) {
//                mobula.test("")
//            }

            myViewModel.uiState.test {
                assertEquals(
                    AppState.Done(cryptoReponse),
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