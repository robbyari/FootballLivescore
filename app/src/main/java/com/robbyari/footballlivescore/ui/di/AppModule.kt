package com.robbyari.footballlivescore.ui.di

import com.robbyari.core.domain.usecase.MatchInteractor
import com.robbyari.core.domain.usecase.MatchUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideMatchUseCase(matchInteractor: MatchInteractor): MatchUseCase
}