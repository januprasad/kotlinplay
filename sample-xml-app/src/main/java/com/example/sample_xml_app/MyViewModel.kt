package com.example.sample_xml_app

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    val game = Game() // init the object
    val _userScore = MutableLiveData<Game>()
    val userScore: LiveData<Game>
        get() = _userScore

    init {
        Log.v("Application", "VM init")
        _userScore.value =
            game.apply {
                score = 1
            }
    }

    fun scorePoint() {
        _userScore.value =
            game.apply {
                score++
            }
    }

    override fun onCleared() {
        super.onCleared()
        Log.v("Application", "VM onCleared")
    }
}
