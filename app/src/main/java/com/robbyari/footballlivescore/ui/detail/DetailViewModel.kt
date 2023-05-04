package com.robbyari.footballlivescore.ui.detail

import androidx.lifecycle.ViewModel
import com.robbyari.core.domain.model.Match
import com.robbyari.core.domain.usecase.MatchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val matchUseCase: MatchUseCase) : ViewModel() {
    fun setFavoriteMatch(match: Match?, newStatus: Boolean?) =
        matchUseCase.setFavoriteMatch(match, newStatus)
}