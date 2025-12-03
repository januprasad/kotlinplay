package com.github.kotlin_tryout.mockkk

interface MainContract {

    interface Presenter {
        fun fetchData()
        fun fetchDataViaCallback()
        fun fetchDataViaRX()
    }

    interface View {
        fun onResult(result: List<UiDataModel>)
        fun onError(error: Throwable)
    }
}