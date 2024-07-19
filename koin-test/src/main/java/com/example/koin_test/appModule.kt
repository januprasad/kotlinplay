package com.example.koin_test

import org.koin.dsl.module

val appModule =
    module {
        // Single instance of a service
        single { MyService() }

        // Factory for creating repositories
        factory { MyRepository() }
    }

class MyService {
    fun doSomething() {
    }
    // ... service implementation
}

class MyRepository {
    fun getData(): String = "Sample"
    // ... repository implementation
}
