package com.robbyari.core.di

import com.robbyari.core.data.FootballRepository
import com.robbyari.core.domain.repository.IFootballRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(footballRepository: FootballRepository): IFootballRepository
}