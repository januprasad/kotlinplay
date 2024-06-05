package com.github.interview_prep.mockkk

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