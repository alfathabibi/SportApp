package org.d3if3049.sportapp.core.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.d3if3049.sportapp.core.data.Resource
import org.d3if3049.sportapp.core.domain.model.Sport

interface SportUseCase {
    fun getAllSport(): Flow<Resource<List<Sport>>>

    fun getFavoriteSport(): Flow<List<Sport>>

    fun setFavoriteSport(tourism: Sport, newState: Boolean)
}