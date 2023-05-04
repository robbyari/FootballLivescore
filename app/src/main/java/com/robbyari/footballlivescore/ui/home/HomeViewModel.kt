package com.robbyari.footballlivescore.ui.home


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.robbyari.core.domain.usecase.MatchUseCase
import com.robbyari.core.utils.DateChooser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(matchUseCase: MatchUseCase) : ViewModel() {
    private val _leagueId = MutableLiveData<String>()
    private val _name = MutableLiveData<String>()

    val lastMatch = matchUseCase.getAllMatch().asLiveData()
    val todayMatch = matchUseCase.getTodayMatch().asLiveData()
    val filterMatch = _leagueId.switchMap { id ->
        matchUseCase.getFilterMatch(id).asLiveData()
    }
    val filterTodayMatch = _leagueId.switchMap { id ->
        matchUseCase.getFilterTodayMatch(DateChooser.getToDate(), id).asLiveData()
    }
    val searchTeam = _name.switchMap { name ->
        matchUseCase.getSearchTeam(name).asLiveData()
    }

    fun setSearchName(name: String) {
        _name.value = name
    }

    fun setLeagueId(id: String) {
        _leagueId.value = id
    }
}