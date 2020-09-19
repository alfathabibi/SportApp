package org.d3if3049.sportapp.core.domain.repository

import kotlinx.coroutines.flow.Flow
import org.d3if3049.sportapp.core.data.Resource
import org.d3if3049.sportapp.core.data.source.local.entity.SportEntity
import org.d3if3049.sportapp.core.domain.model.Sport

interface ISportRepository {
    fun getAllSport(): Flow<Resource<List<Sport>>>

    fun getFavoriteSport(): Flow<List<Sport>>

    fun setFavoriteSport(sport: Sport, newState: Boolean)
}