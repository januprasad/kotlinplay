package com.example.sample_xml_app

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NumberViewModel(
    someArgument: Int,
) : ViewModel() {
    val number = MutableLiveData<Int>()

    init {
        number.postValue(someArgument)
        number.value = someArgument
    }
}
