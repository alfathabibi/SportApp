package org.d3if3049.sportapp.core.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.d3if3049.sportapp.core.data.source.local.LocalDataSource
import org.d3if3049.sportapp.core.data.source.remote.RemoteDataSource
import org.d3if3049.sportapp.core.data.source.remote.network.ApiResponse
import org.d3if3049.sportapp.core.data.source.remote.response.SportResponse
import org.d3if3049.sportapp.core.domain.model.Sport
import org.d3if3049.sportapp.core.domain.repository.ISportRepository
import org.d3if3049.sportapp.core.utils.AppExecutors
import org.d3if3049.sportapp.core.utils.DataMapper

class SportRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): ISportRepository{

//    companion object {
//        @Volatile
//        private var instance: SportRepository? = null
//
//        fun getInstance(
//            remoteData: RemoteDataSource,
//            localData: LocalDataSource,
//            appExecutors: AppExecutors
//        ): SportRepository =
//            instance ?: synchronized(this) {
//                instance ?: SportRepository(remoteData, localData, appExecutors)
//            }
//    }

    override fun getAllSport(): Flow<Resource<List<Sport>>> =
        object : NetworkBoundResource<List<Sport>, List<SportResponse>>() {
            override fun loadFromDB(): Flow<List<Sport>> {
                return localDataSource.getAllSport().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Sport>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<SportResponse>>> =
                remoteDataSource.getAllTourism()

            override suspend fun saveCallResult(data: List<SportResponse>) {
                val sportList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertSport(sportList)
            }
        }.asFlow()

    override fun getFavoriteSport(): Flow<List<Sport>> {
        return localDataSource.getFavoriteSport().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteSport(sport: Sport, newState: Boolean) {
        val sportEntity = DataMapper.mapDomainToEntity(sport)
        appExecutors.diskIO().execute{ localDataSource.setFavoriteSport(sportEntity, newState)}
    }
}