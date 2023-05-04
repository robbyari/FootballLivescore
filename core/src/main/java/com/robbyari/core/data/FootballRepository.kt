package com.robbyari.core.data

import com.robbyari.core.data.source.local.LocalDataSource
import com.robbyari.core.data.source.remote.RemoteDataSource
import com.robbyari.core.data.source.remote.network.ApiResponse
import com.robbyari.core.data.source.remote.reponse.MatchResponse
import com.robbyari.core.domain.model.Match
import com.robbyari.core.domain.repository.IFootballRepository
import com.robbyari.core.utils.AppExecutors
import com.robbyari.core.utils.DataMapper
import com.robbyari.core.utils.DateChooser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FootballRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IFootballRepository {

    override fun getAllMatch(): Flow<Resource<List<Match>>> =
        object : NetworkBoundResources<List<Match>, List<MatchResponse>>() {
            override fun loadFromDB(): Flow<List<Match>> {
                return localDataSource.getAllMatch().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Match>?): Boolean =
                data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MatchResponse>>> =
                remoteDataSource.getAllMatch()

            override suspend fun saveCallResult(data: List<MatchResponse>) {
                val matchList = DataMapper.mapResponseToEntities(data)
                localDataSource.insertMatch(matchList)
            }
        }.asFlow()

    override fun getTodayMatch(): Flow<Resource<List<Match>>> =
        object : NetworkBoundResources<List<Match>, List<MatchResponse>>() {
            override fun loadFromDB(): Flow<List<Match>> {
                return localDataSource.getTodayMatch(DateChooser.getToDate()).map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Match>?): Boolean =
                data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MatchResponse>>> =
                remoteDataSource.getAllMatch()

            override suspend fun saveCallResult(data: List<MatchResponse>) {
                val matchList = DataMapper.mapResponseToEntities(data)
                localDataSource.insertMatch(matchList)
            }
        }.asFlow()

    override fun getFilterMatch(id: String): Flow<Resource<List<Match>>> =
        object : NetworkBoundResources<List<Match>, List<MatchResponse>>() {
            override fun loadFromDB(): Flow<List<Match>> {
                return localDataSource.getFilterMatch(id).map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MatchResponse>>> =
                remoteDataSource.getAllMatch()

            override fun shouldFetch(data: List<Match>?): Boolean =
                data == null

            override suspend fun saveCallResult(data: List<MatchResponse>) {
                val matchList = DataMapper.mapResponseToEntities(data)
                localDataSource.insertMatch(matchList)
            }
        }.asFlow()

    override fun getFilterTodayMatch(localDate: String, id: String): Flow<Resource<List<Match>>> =
        object : NetworkBoundResources<List<Match>, List<MatchResponse>>() {
            override fun loadFromDB(): Flow<List<Match>> {
                return localDataSource.getFilterTodayMatch(localDate, id).map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MatchResponse>>> =
                remoteDataSource.getAllMatch()

            override fun shouldFetch(data: List<Match>?): Boolean =
                data == null

            override suspend fun saveCallResult(data: List<MatchResponse>) {
                val matchList = DataMapper.mapResponseToEntities(data)
                localDataSource.insertMatch(matchList)
            }
        }.asFlow()

    override fun getSearchTeam(name: String): Flow<Resource<List<Match>>> =
        object : NetworkBoundResources<List<Match>, List<MatchResponse>>() {
            override fun loadFromDB(): Flow<List<Match>> {
                return localDataSource.getSearchTeam(name).map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MatchResponse>>> =
                remoteDataSource.getAllMatch()

            override fun shouldFetch(data: List<Match>?): Boolean =
                data == null

            override suspend fun saveCallResult(data: List<MatchResponse>) {
                val matchList = DataMapper.mapResponseToEntities(data)
                localDataSource.insertMatch(matchList)
            }
        }.asFlow()

    override fun getFavoriteMatch(): Flow<List<Match>> {
        return localDataSource.getFavoriteMatch().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteMatch(match: Match?, state: Boolean?) {
        val matchEntity = DataMapper.mapDomainToEntity(match)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMatch(matchEntity, state) }
    }

}