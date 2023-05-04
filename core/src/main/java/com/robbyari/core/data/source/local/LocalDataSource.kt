package com.robbyari.core.data.source.local

import com.robbyari.core.data.source.local.entity.MatchEntity
import com.robbyari.core.data.source.local.room.MatchDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val matchDao: MatchDao) {
    fun getAllMatch(): Flow<List<MatchEntity>> = matchDao.getAllMatch()
    fun getTodayMatch(localDate: String): Flow<List<MatchEntity>> =
        matchDao.getTodayMatch(localDate)

    fun getFilterMatch(id: String): Flow<List<MatchEntity>> = matchDao.getFilterMatch(id)
    fun getFilterTodayMatch(localDate: String, id: String): Flow<List<MatchEntity>> =
        matchDao.getFilterTodayMatch(localDate, id)

    fun getSearchTeam(name: String): Flow<List<MatchEntity>> = matchDao.getSearchTeam(name)
    suspend fun insertMatch(matchList: List<MatchEntity>) = matchDao.insertMatch(matchList)
    fun getFavoriteMatch(): Flow<List<MatchEntity>> = matchDao.getFavoriteMatch()
    fun setFavoriteMatch(match: MatchEntity, newState: Boolean?) {
        match.isFavorite = newState
        matchDao.updateFavoriteMatch(match)
    }
}