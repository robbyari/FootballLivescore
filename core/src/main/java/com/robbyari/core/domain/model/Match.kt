package com.robbyari.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Match(
    val matchId: String,
    val leagueId: String?,
    val leagueName: String?,
    val matchDate: String?,
    val matchTime: String?,
    val matchStatus: String?,
    val matchHometeamName: String?,
    val matchAwayteamName: String?,
    val matchHometeamScore: String?,
    val matchAwayteamScore: String?,
    val matchStadium: String?,
    val teamHomeBadge: String?,
    val teamAwayBadge: String?,
    val leagueLogo: String?,
    val matchReferee: String?,
    val matchHometeamSystem: String?,
    val matchAwayteamSystem: String?,
    val leagueYear: String?,
    val matchHometeamHalftimeScore: String?,
    val matchAwayteamHalftimeScore: String?,
    val matchHometeamFtScore: String?,
    val matchAwayteamFtScore: String?,
    val matchRound: String?,
    val isFavorite: Boolean?
) : Parcelable
