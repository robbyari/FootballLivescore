package com.robbyari.core.data.source.remote

import android.util.Log
import com.robbyari.core.data.source.remote.network.ApiResponse
import com.robbyari.core.data.source.remote.network.ApiService
import com.robbyari.core.data.source.remote.reponse.MatchResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun getAllMatch(): Flow<ApiResponse<List<MatchResponse>>> {
        return flow {
            try {
                val response = apiService.getEvent()
                if (response.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}