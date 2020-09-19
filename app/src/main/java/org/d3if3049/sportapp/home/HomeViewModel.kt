package org.d3if3049.sportapp.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import org.d3if3049.sportapp.core.domain.usecase.SportUseCase

class HomeViewModel (sportUseCase: SportUseCase): ViewModel() {
    val sport = sportUseCase.getAllSport().asLiveData()
}