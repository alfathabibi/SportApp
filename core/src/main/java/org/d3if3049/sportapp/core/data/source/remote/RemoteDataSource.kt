package org.d3if3049.sportapp.core.data.source.remote

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.d3if3049.sportapp.core.data.source.remote.network.ApiResponse
import org.d3if3049.sportapp.core.data.source.remote.network.ApiService
import org.d3if3049.sportapp.core.data.source.remote.response.SportResponse

class RemoteDataSource(private val apiService: ApiService) {
//    companion object {
//        @Volatile
//        private var instance: RemoteDataSource? = null
//
//        fun getInstance(service: ApiService): RemoteDataSource =
//            instance ?: synchronized(this) {
//                instance ?: RemoteDataSource(service)
//            }
//    }

    suspend fun getAllTourism(): Flow<ApiResponse<List<SportResponse>>> {

        return flow{
            try {
                val response = apiService.getList()
                val dataArray = response.sports
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.sports))
                } else {
                    emit(ApiResponse.Empty)
                }
            }catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("Remote Data Source", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}