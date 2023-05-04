package com.robbyari.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.robbyari.core.domain.usecase.MatchUseCase
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(matchUseCase: MatchUseCase) : ViewModel() {
    val favoriteMatch = matchUseCase.getFavoriteMatch().asLiveData()
}