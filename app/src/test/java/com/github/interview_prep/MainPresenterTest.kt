package com.github.interview_prep

import com.github.interview_prep.mockkk.DataModel
import com.github.interview_prep.mockkk.DataRepository
import com.github.interview_prep.mockkk.MainContract
import com.github.interview_prep.mockkk.MainPresenter
import com.github.interview_prep.mockkk.UiDataModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.slot
import io.mockk.unmockkAll
import io.mockk.verify
import io.reactivex.rxjava3.core.Single
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.After
import org.junit.Before
import org.junit.Test

class MainPresenterTest {
    @RelaxedMockK
    lateinit var view: MainContract.View

    @RelaxedMockK
    lateinit var dataRepository: DataRepository

    lateinit var mainPresenter: MainContract.Presenter

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        mainPresenter = MainPresenter(view, dataRepository)
    }

    @Test
    fun `test fetchData with an empty result`() {
        // it mocks the real data to empty list on every test
        every { dataRepository.fetchData() } returns listOf()
        // when it call it release the mock value
        mainPresenter.fetchData()

        val captureData = slot<List<UiDataModel>>()

        // check inside that exact call how many time it called.
        verify(exactly = 1) { view.onResult(capture(captureData)) }
        captureData.captured.let { res ->
            assertNotNull(res)
            assert(res.isEmpty())
        }
    }

    @Test
    fun `test fetchDataViaCallback with an result`() {
        val captureCallback = slot<(data: List<DataModel>) -> Unit>()
        every { dataRepository.fetchData(capture(captureCallback)) } answers {
            val fakeList = listOf(DataModel(1, "value 1"))
            captureCallback.captured.invoke(fakeList)
        }
        mainPresenter.fetchDataViaCallback()
        val captureData = slot<List<UiDataModel>>()
        verify(exactly = 1) { view.onResult(capture(captureData)) }
        captureData.captured.let { res ->
            assertNotNull(res)
            assert(res.isNotEmpty())
            val first = res.first()
            assertEquals("value 1", first.value)
        }
    }

    @Test
    fun `Test fetchData with an exception`() {
        every { dataRepository.fetchData() } throws IllegalStateException("Houston we have a problem")
        mainPresenter.fetchData()

        verify(exactly = 0) { view.onResult(any()) }
        verify(exactly = 1) { view.onError(any()) }
    }

    @Test
    fun `test fetchDataViaRX with an empty result`() {
        every { dataRepository.fetchDataWithRX() } returns Single.just(listOf())
        mainPresenter.fetchDataViaRX()
        val captureData = slot<List<UiDataModel>>()
        verify(exactly = 1) { view.onResult(capture(captureData)) }
        captureData.captured.let { res ->
            assertNotNull(res)
            assert(res.isEmpty())
        }
    }

    @Test
    fun `test fetchDataViaRX with an valid result`() {
        every { dataRepository.fetchDataWithRX() } returns Single.just(listOf(DataModel(1, "value 1")))
        mainPresenter.fetchDataViaRX()
        val captureData = slot<List<UiDataModel>>()
        verify(exactly = 1) { view.onResult(capture(captureData)) }
        captureData.captured.let { res ->
            assertNotNull(res)
            assert(res.isNotEmpty())
            assertEquals("value 1", res.first().value)
        }
    }

    @Test
    fun `test fetchDataViaRX with ex`() {
        every { dataRepository.fetchDataWithRX() } returns Single.error(Throwable("Oh oh something is broken"))
        mainPresenter.fetchDataViaRX()
        verify(exactly = 0) { view.onResult(any()) }
        verify(exactly = 1) { view.onError(any()) }
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}
