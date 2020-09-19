package org.d3if3049.sportapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListSportResponse (
    @field:SerializedName("sports")
    val sports: List<SportResponse>
)