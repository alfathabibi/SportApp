package org.d3if3049.sportapp.detail

import androidx.lifecycle.ViewModel
import org.d3if3049.sportapp.core.domain.model.Sport
import org.d3if3049.sportapp.core.domain.usecase.SportUseCase

class DetailSportViewModel(private val sportUseCase: SportUseCase): ViewModel() {
    fun setFavoriteSport(sport: Sport, newState: Boolean) = sportUseCase.setFavoriteSport(sport, newState)
}