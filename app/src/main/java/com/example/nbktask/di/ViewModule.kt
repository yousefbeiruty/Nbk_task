package com.example.nbktask.di

import android.content.Context
import com.example.nbktask.common.ProgressDialog
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object ViewModule {
    @Provides
    @ActivityScoped
    fun getProgressBar(@ActivityContext context: Context): ProgressDialog {
        return ProgressDialog(
            context
        )
    }
}