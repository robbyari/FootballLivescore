package com.robbyari.core.utils

import com.robbyari.core.data.source.local.entity.MatchEntity
import com.robbyari.core.data.source.remote.reponse.MatchResponse
import com.robbyari.core.domain.model.Match

object DataMapper {
    fun mapResponseToEntities(input: List<MatchResponse>): List<MatchEntity> {
        val matchList = ArrayList<MatchEntity>()
        input.map {
            val match = MatchEntity(
                matchId = it.matchId,
                leagueId = it.leagueId,
                leagueName = it.leagueName,
                matchDate = it.matchDate,
                matchStatus = it.matchStatus,
                matchTime = it.matchTime,
                matchHometeamName = it.matchHometeamName,
                matchAwayteamName = it.matchAwayteamName,
                matchHometeamScore = it.matchHometeamScore,
                matchAwayteamScore = it.matchAwayteamScore,
                matchStadium = it.matchStadium,
                teamHomeBadge = it.teamHomeBadge,
                teamAwayBadge = it.teamAwayBadge,
                leagueLogo = it.leagueLogo,
                matchHometeamSystem = it.matchHometeamSystem,
                matchAwayteamSystem = it.matchAwayteamSystem,
                matchHometeamHalftimeScore = it.matchHometeamHalftimeScore,
                matchAwayteamHalftimeScore = it.matchAwayteamHalftimeScore,
                matchHometeamFtScore = it.matchHometeamFtScore,
                matchAwayteamFtScore = it.matchAwayteamFtScore,
                leagueYear = it.leagueYear,
                matchReferee = it.matchReferee,
                matchRound = it.matchRound,
                isFavorite = false
            )
            matchList.add(match)
        }
        return matchList

    }

    fun mapEntitiesToDomain(input: List<MatchEntity>): List<Match> =
        input.map {
            Match(
                matchId = it.matchId,
                leagueId = it.leagueId,
                leagueName = it.leagueName,
                matchDate = it.matchDate,
                matchStatus = it.matchStatus,
                matchTime = it.matchTime,
                matchHometeamName = it.matchHometeamName,
                matchAwayteamName = it.matchAwayteamName,
                matchHometeamScore = it.matchHometeamScore,
                matchAwayteamScore = it.matchAwayteamScore,
                matchStadium = it.matchStadium,
                teamHomeBadge = it.teamHomeBadge,
                teamAwayBadge = it.teamAwayBadge,
                leagueLogo = it.leagueLogo,
                matchHometeamSystem = it.matchHometeamSystem,
                matchAwayteamSystem = it.matchAwayteamSystem,
                matchHometeamHalftimeScore = it.matchHometeamHalftimeScore,
                matchAwayteamHalftimeScore = it.matchAwayteamHalftimeScore,
                matchHometeamFtScore = it.matchHometeamFtScore,
                matchAwayteamFtScore = it.matchAwayteamFtScore,
                leagueYear = it.leagueYear,
                matchReferee = it.matchReferee,
                matchRound = it.matchRound,
                isFavorite = it.isFavorite
            )
        }


    fun mapDomainToEntity(input: Match?) = MatchEntity(
        matchId = input!!.matchId,
        leagueId = input.leagueId,
        leagueName = input.leagueName,
        matchDate = input.matchDate,
        matchStatus = input.matchStatus,
        matchTime = input.matchTime,
        matchHometeamName = input.matchHometeamName,
        matchAwayteamName = input.matchAwayteamName,
        matchHometeamScore = input.matchHometeamScore,
        matchAwayteamScore = input.matchAwayteamScore,
        matchStadium = input.matchStadium,
        teamHomeBadge = input.teamHomeBadge,
        teamAwayBadge = input.teamAwayBadge,
        leagueLogo = input.leagueLogo,
        matchHometeamSystem = input.matchHometeamSystem,
        matchAwayteamSystem = input.matchAwayteamSystem,
        matchHometeamHalftimeScore = input.matchHometeamHalftimeScore,
        matchAwayteamHalftimeScore = input.matchAwayteamHalftimeScore,
        matchHometeamFtScore = input.matchHometeamFtScore,
        matchAwayteamFtScore = input.matchAwayteamFtScore,
        leagueYear = input.leagueYear,
        matchReferee = input.matchReferee,
        matchRound = input.matchRound,
        isFavorite = input.isFavorite
    )

}