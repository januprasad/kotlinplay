package com.github.januprasad.hilt_example_2.intro

import android.content.Context
import com.github.januprasad.hilt_example_2.intro.repo.MailClient
import com.github.januprasad.hilt_example_2.intro.repo.MailClientImp
import com.github.januprasad.hilt_example_2.intro.repo.SettingsRepo
import com.github.januprasad.hilt_example_2.intro.repo.SettingsRepoImp
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext


@Module
@InstallIn(ViewModelComponent::class)
abstract class SettingsDi {

    @Binds
    abstract fun provideAppSettings(appSettings: SettingsRepoImp): SettingsRepo

}

@Module
@InstallIn(ViewModelComponent::class)
object MailDi {
    @Provides
    fun provideEmailClient(@ApplicationContext context: Context): MailClient =
        MailClientImp(context)

}

