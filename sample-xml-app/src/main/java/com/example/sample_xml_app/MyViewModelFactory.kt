package com.example.sample_xml_app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MyViewModelFactory(
    private val someArgument: Int,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NumberViewModel::class.java)) {
            return NumberViewModel(someArgument) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
