package com.robbyari.core.data.source.remote.network

import com.robbyari.core.BuildConfig
import com.robbyari.core.data.source.remote.reponse.MatchResponse
import com.robbyari.core.utils.DateChooser
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("?action=get_events")
    suspend fun getEvent(
        @Query("timezone") timezone: String? = "Asia/Jakarta",
        @Query("from") from: String? = DateChooser.getFromDate(),
        @Query("to") to: String? = DateChooser.getToDate(),
        @Query("league_id") league: String? = "194,152,207,302,175,168",
        @Query("APIkey") key: String? = BuildConfig.APIKEY
    ): List<MatchResponse>

}