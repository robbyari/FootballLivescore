package com.robbyari.core.domain.usecase

import com.robbyari.core.domain.model.Match
import com.robbyari.core.domain.repository.IFootballRepository
import javax.inject.Inject

class MatchInteractor @Inject constructor(private val footballRepository: IFootballRepository) :
    MatchUseCase {
    override fun getAllMatch() = footballRepository.getAllMatch()
    override fun getTodayMatch() = footballRepository.getTodayMatch()
    override fun getFilterMatch(id: String) = footballRepository.getFilterMatch(id)
    override fun getFilterTodayMatch(localDate: String, id: String) =
        footballRepository.getFilterTodayMatch(localDate, id)

    override fun getSearchTeam(name: String) = footballRepository.getSearchTeam(name)
    override fun getFavoriteMatch() = footballRepository.getFavoriteMatch()
    override fun setFavoriteMatch(match: Match?, state: Boolean?) =
        footballRepository.setFavoriteMatch(match, state)


}