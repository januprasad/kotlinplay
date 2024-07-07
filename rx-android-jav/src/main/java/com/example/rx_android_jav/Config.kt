package com.example.rx_android_jav

object Config {
    enum class Environment {
        DEV,
        STAGING,
        PRODUCTION,
        HOTFIX,
    }

    lateinit var environment: Environment
        private set

    init {
//        when (BuildConfig.ENV_NAME) {
//        }
    }
}
