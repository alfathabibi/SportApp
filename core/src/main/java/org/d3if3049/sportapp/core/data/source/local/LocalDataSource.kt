package org.d3if3049.sportapp.core.data.source.local

import kotlinx.coroutines.flow.Flow
import org.d3if3049.sportapp.core.data.source.local.entity.SportEntity
import org.d3if3049.sportapp.core.data.source.local.room.SportDao

class LocalDataSource(private val sportDao: SportDao){

//    companion object{
//        private var instance: LocalDataSource? = null
//
//        fun getInstance(sportDao: SportDao): LocalDataSource =
//            instance ?: synchronized(this) {
//                instance ?: LocalDataSource(sportDao)
//            }
//    }

    fun getAllSport(): Flow<List<SportEntity>> = sportDao.getAllSport()

    fun getFavoriteSport(): Flow<List<SportEntity>> = sportDao.getFavoriteSport()

    suspend fun insertSport(sportList: List<SportEntity>) = sportDao.insertSport(sportList)

    fun setFavoriteSport(sport: SportEntity, newState: Boolean){
        sport.isFavorite = newState
        sportDao.updateFavoriteTourism(sport)
    }
}