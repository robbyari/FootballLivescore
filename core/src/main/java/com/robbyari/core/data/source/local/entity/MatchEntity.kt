package com.robbyari.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "match")
data class MatchEntity(
    @PrimaryKey
    @ColumnInfo(name = "match_id")
    var matchId: String,

    @ColumnInfo(name = "league_id")
    var leagueId: String?,

    @ColumnInfo(name = "league_name")
    var leagueName: String?,

    @ColumnInfo(name = "match_date")
    var matchDate: String?,

    @ColumnInfo(name = "match_time")
    var matchTime: String?,

    @ColumnInfo(name = "match_status")
    var matchStatus: String?,

    @ColumnInfo(name = "match_hometeam_name")
    var matchHometeamName: String?,

    @ColumnInfo(name = "match_awayteam_name")
    var matchAwayteamName: String?,

    @ColumnInfo(name = "match_hometeam_score")
    var matchHometeamScore: String?,

    @ColumnInfo(name = "match_awayteam_score")
    var matchAwayteamScore: String?,

    @ColumnInfo(name = "match_stadium")
    var matchStadium: String?,

    @ColumnInfo(name = "team_home_badge")
    var teamHomeBadge: String?,

    @ColumnInfo(name = "team_away_badge")
    var teamAwayBadge: String?,

    @ColumnInfo(name = "league_logo")
    var leagueLogo: String?,

    //

    @ColumnInfo(name = "match_hometeam_system")
    var matchHometeamSystem: String?,

    @ColumnInfo(name = "match_awayteam_system")
    var matchAwayteamSystem: String?,

    @ColumnInfo(name = "match_hometeam_halftime_score")
    val matchHometeamHalftimeScore: String?,

    @ColumnInfo(name = "match_awayteam_halftime_score")
    val matchAwayteamHalftimeScore: String?,

    @ColumnInfo(name = "match_hometeam_ft_score")
    val matchHometeamFtScore: String?,

    @ColumnInfo(name = "match_awayteam_ft_score")
    val matchAwayteamFtScore: String?,

    @ColumnInfo(name = "match_referee")
    var matchReferee: String?,

    @ColumnInfo(name = "league_year")
    var leagueYear: String?,

    @ColumnInfo(name = "match_round")
    val matchRound: String?,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean? = false
)
