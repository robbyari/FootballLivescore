package com.robbyari.footballlivescore.ui.di

import com.robbyari.core.domain.usecase.MatchUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModuleDependencies {

    fun matchUseCase(): MatchUseCase
}