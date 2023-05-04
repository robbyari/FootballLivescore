package com.robbyari.core.domain.usecase

import com.robbyari.core.data.Resource
import com.robbyari.core.domain.model.Match
import kotlinx.coroutines.flow.Flow


interface MatchUseCase {
    fun getAllMatch(): Flow<Resource<List<Match>>>
    fun getTodayMatch(): Flow<Resource<List<Match>>>
    fun getFilterMatch(id: String): Flow<Resource<List<Match>>>
    fun getFilterTodayMatch(localDate: String, id: String): Flow<Resource<List<Match>>>
    fun getSearchTeam(name: String): Flow<Resource<List<Match>>>
    fun getFavoriteMatch(): Flow<List<Match>>
    fun setFavoriteMatch(match: Match?, state: Boolean?)

}