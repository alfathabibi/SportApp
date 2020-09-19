package org.d3if3049.sportapp.core.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.d3if3049.sportapp.core.data.Resource
import org.d3if3049.sportapp.core.domain.model.Sport
import org.d3if3049.sportapp.core.domain.repository.ISportRepository

class SportInteractor (private val sportRepository: ISportRepository): SportUseCase {
    override fun getAllSport(): Flow<Resource<List<Sport>>> {
        return sportRepository.getAllSport()
    }

    override fun getFavoriteSport(): Flow<List<Sport>> {
        return sportRepository.getFavoriteSport()
    }

    override fun setFavoriteSport(tourism: Sport, newState: Boolean) {
        return sportRepository.setFavoriteSport(tourism, newState)
    }
}