package com.github.interview_prep.mockkk

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import java.util.UUID

class MainPresenter(
    private val view: MainContract.View,
    private val dataRepository: DataRepository
) : MainContract.Presenter {

    override fun fetchData() {
        try {
            val result = dataRepository.fetchData()

            view.onResult(result.map { UiDataModel(UUID.randomUUID().toString(), it.id, it.value) })
        } catch (err: Exception) {
            view.onError(err)
        }
    }

    override fun fetchDataViaCallback() {
        try {
            dataRepository.fetchData { data ->
                view.onResult(data.map {
                    UiDataModel(
                        UUID.randomUUID().toString(),
                        it.id,
                        it.value
                    )
                })
            }
        } catch (err: Exception) {
            view.onError(err)
        }
    }

    override fun fetchDataViaRX() {
        dataRepository.fetchDataWithRX()
            .flatMap { result ->
                val output = result.map {
                    UiDataModel(UUID.randomUUID().toString(), it.id, it.value)
                }
                Single.just(output)
            }.subscribe(object : SingleObserver<List<UiDataModel>> {
                override fun onSuccess(t: List<UiDataModel>) {
                    view.onResult(t)
                }

                override fun onSubscribe(d: Disposable) {}

                override fun onError(e: Throwable) {
                    view.onError(e)
                }
            })

    }
}