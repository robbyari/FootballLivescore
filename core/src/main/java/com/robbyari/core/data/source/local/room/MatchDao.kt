package com.robbyari.core.data.source.local.room

import androidx.room.*
import com.robbyari.core.data.source.local.entity.MatchEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MatchDao {
    @Query("SELECT * FROM 'match'")
    fun getAllMatch(): Flow<List<MatchEntity>>

    @Query("SELECT * FROM 'match' WHERE match_date = :localDate")
    fun getTodayMatch(localDate: String): Flow<List<MatchEntity>>

    @Query("SELECT * FROM 'match' WHERE league_id = :id")
    fun getFilterMatch(id: String): Flow<List<MatchEntity>>

    @Query("SELECT * FROM 'match' WHERE match_date = :localDate AND league_id = :id")
    fun getFilterTodayMatch(localDate: String, id: String): Flow<List<MatchEntity>>

    @Query("SELECT * FROM 'match' WHERE match_hometeam_name LIKE '%' || :name || '%' OR match_awayteam_name LIKE '%' || :name || '%'")
    fun getSearchTeam(name: String): Flow<List<MatchEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMatch(match: List<MatchEntity>)

    @Query("SELECT * FROM 'match' WHERE isFavorite = 1")
    fun getFavoriteMatch(): Flow<List<MatchEntity>>

    @Update
    fun updateFavoriteMatch(match: MatchEntity)
}