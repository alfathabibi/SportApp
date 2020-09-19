package org.d3if3049.sportapp.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import org.d3if3049.sportapp.core.domain.usecase.SportUseCase

class FavoriteViewModel(sportUseCase: SportUseCase): ViewModel() {
    val favoriteSport = sportUseCase.getFavoriteSport().asLiveData()
}