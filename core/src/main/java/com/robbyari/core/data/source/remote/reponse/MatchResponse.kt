package com.robbyari.core.data.source.remote.reponse

import com.google.gson.annotations.SerializedName

data class MatchResponse(

    @field:SerializedName("match_awayteam_name")
    val matchAwayteamName: String? = null,

    @field:SerializedName("match_awayteam_ft_score")
    val matchAwayteamFtScore: String? = null,

    @field:SerializedName("league_logo")
    val leagueLogo: String? = null,

    @field:SerializedName("match_hometeam_halftime_score")
    val matchHometeamHalftimeScore: String? = null,

    @field:SerializedName("match_referee")
    val matchReferee: String? = null,

    @field:SerializedName("league_year")
    val leagueYear: String? = null,

    @field:SerializedName("match_awayteam_halftime_score")
    val matchAwayteamHalftimeScore: String? = null,

    @field:SerializedName("match_date")
    val matchDate: String? = null,

    @field:SerializedName("match_hometeam_system")
    val matchHometeamSystem: String? = null,

    @field:SerializedName("match_awayteam_score")
    val matchAwayteamScore: String? = null,

    @field:SerializedName("team_home_badge")
    val teamHomeBadge: String? = null,

    @field:SerializedName("match_stadium")
    val matchStadium: String? = null,

    @field:SerializedName("match_hometeam_ft_score")
    val matchHometeamFtScore: String? = null,

    @field:SerializedName("match_id")
    val matchId: String,

    @field:SerializedName("league_name")
    val leagueName: String? = null,

    @field:SerializedName("match_time")
    val matchTime: String? = null,

    @field:SerializedName("match_awayteam_system")
    val matchAwayteamSystem: String? = null,

    @field:SerializedName("match_status")
    val matchStatus: String? = null,

    @field:SerializedName("match_hometeam_score")
    val matchHometeamScore: String? = null,

    @field:SerializedName("match_hometeam_name")
    val matchHometeamName: String? = null,

    @field:SerializedName("match_round")
    val matchRound: String? = null,

    @field:SerializedName("league_id")
    val leagueId: String? = null,

    @field:SerializedName("team_away_badge")
    val teamAwayBadge: String? = null,

    )




