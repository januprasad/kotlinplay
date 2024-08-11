package com.example.koin_test

import android.content.SharedPreferences
import com.example.koin_test.delegation.delegates
import com.example.koin_test.delegation.getValue
import com.example.koin_test.delegation.setValue

class SettingsRepository(
    preferences: SharedPreferences,
) {
    /** Simple delegation - See [SharedPreferences.getValue] in `DelegatesSimple` */
    var nickname: String by preferences

    /** Advanced delegation - See [SharedPreferences.delegates] in `DelegatesAdvanced` */
    var turboMode: Boolean by preferences.delegates.boolean(true, "TURBO_MODE")
    var throttle: Float by preferences.delegates.float(3.5f)
}
