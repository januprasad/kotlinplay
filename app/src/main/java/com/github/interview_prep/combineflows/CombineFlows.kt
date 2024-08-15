package com.github.interview_prep.combineflows

// fun main() {
//    val numbers = flowOf(1, 2, 3, 4, 5, 6, 7, 8)
//    val names = flowOf("Alice", "Bob", "Michael", "Lissa")
//    val liveData = MutableLiveData<Pair<Int, String>>()
//    runBlocking {
//        launch {
//            numbers
//                .combine(names) { nums, nams ->
//                    liveData.postValue(
//                        Pair(nums, nams),
//                    )
//                    println("$nums $nams")
//                }.collect()
//        }
//    }
// }
