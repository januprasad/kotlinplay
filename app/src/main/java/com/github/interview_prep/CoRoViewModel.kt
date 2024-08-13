package com.github.interview_prep

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class CoRoViewModel : ViewModel() {
    private val _state = MutableLiveData(0)
    val state: LiveData<Int> = _state

    fun test() {
        val coroutineExceptionHandler =
            CoroutineExceptionHandler { _, exception ->
                println("Handle $exception in CoroutineExceptionHandler")
            }
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
//            executeOneByOne()
//            executeOneAccordingCorotineFree()
//            asyncMode()
//            asyncMode1()
//            launchCoRoExceptionMode()
//            launchCoroScope()
            testGlobalScope()
//            runBlockSample()
        }
    }

    private fun runBlockSample() =
        runBlocking {
            delay(3000L)
            println("Done")
        }

    private suspend fun testGlobalScope() {
        GlobalScope.launch {
            // no lifecycle
            // long running task
            // crash
        }
        val exceptionHandler =
            CoroutineExceptionHandler { _, _ ->
                println("exception")
            }
        CoroutineScope(Dispatchers.Default + exceptionHandler).launch {
            println("Hello Scope")
            runBlocking {
                println("Hello CoroutineScope runBlocking")
                throw ArithmeticException()
            }
        }

        coroutineScope {
            runBlocking {
                println("Hello coroutineScope runBlocking")
            }
            launch {
                println("Hello1")
            }
            launch {
//                delay(2000L)
                println("Hello1")
            }
        }
    }

    fun updateState() {
        viewModelScope.launch {
            val result = asyncModeTest()
            _state.postValue(result)
        }
    }

    private fun launchCoroScope() {
        viewModelScope.launch {
            try {
                coroutineScope {
                    launch {
                        throw IllegalStateException("IllegalStateException in nested coroutine")
                    }

                    // ----------- OR We can also write async/await inside coroutineScope
                    val deferredResult1 =
                        async(Dispatchers.IO) {
                            throw IllegalStateException("Error thrown in async")
                        }
                    deferredResult1.await()
                }
            } catch (e: Exception) {
                println("Handle $e in try/catch")
                // catch error
            }
        }
    }

    private suspend fun launchCoRoExceptionMode() {
        withContext(Dispatchers.IO) {
            launch {
                delay(1000)
                val result = 0 / 0
            }
        }
    }
}

private suspend fun asyncMode1(): Int {
    return withContext(Dispatchers.IO) {
        Log.v("Log", "imagine db operations")
        val result =
            async {
                // imagine db operations
                delay(1000)
                Log.v("Log", "imagine db operations")
                return@async 100
            }
        Log.v("Log", "imagine heavy calculation")
        val result1 =
            async {
                // image heavy calculation
                delay(3000)
                return@async 100 + result.await()
            }
        val resultString = result1.await()
        Log.v("Log", resultString.toString())
    }
}

private suspend fun asyncMode(): Int {
    return withContext(Dispatchers.IO) {
        Log.v("Log", "imagine db operations")
        val result =
            async {
                // imagine db operations
                delay(1000)
                return@async 100
            }
        Log.v("Log", "imagine heavy calculation")
        val result1 =
            async {
                // image heavy calculation
                delay(2000)
                return@async 100
            }
        val resultString = result.await() + result1.await()
        Log.v("Log", resultString.toString())
        resultString
    }
}

private suspend fun asyncModeTest(): Int {
    return withContext(Dispatchers.IO) {
        val one =
            async {
                // imagine db operations
                delay(100)
                return@async 1
            }
        val two =
            async {
                // image heavy calculation
                delay(100)
                return@async 2
            }
        one.await() + two.await()
    }
}

private suspend fun executeOneAccordingCorotineFree() {
    withContext(Dispatchers.IO) {
        launch {
            Log.v("Log1", "before execution")
            delay(5000L)
            Log.v("Log1", "after execution")
        }
        launch {
            Log.v("Log2", "before execution")
            delay(5000L)
            Log.v("Log2", "after execution")
        }
        launch {
            Log.v("Log3", "before execution")
            delay(5000L)
            Log.v("Log3", "after execution")
        }
    }
}

private suspend fun executeOneByOne() {
    withContext(Dispatchers.IO) {
        runBlocking {
            Log.v("Log1", "before execution")
            delay(5000L)
            Log.v("Log1", "after execution")
        }
        runBlocking {
            Log.v("Log2", "before execution")
            delay(5000L)
            Log.v("Log2", "after execution")
        }
        runBlocking {
            Log.v("Log3", "before execution")
            delay(5000L)
            Log.v("Log3", "after execution")
        }
    }
}
