package org.d3if3049.sportapp.core.data.source.remote.network

import org.d3if3049.sportapp.core.data.source.remote.response.ListSportResponse
import retrofit2.http.GET

interface ApiService {
    @GET("all_sports.php")
    suspend fun getList(): ListSportResponse
}