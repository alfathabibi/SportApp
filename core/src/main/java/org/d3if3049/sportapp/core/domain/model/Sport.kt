package org.d3if3049.sportapp.core.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Sport(
    var idSport: String,

    var strSport: String,

    var strFormat: String,

    var strSportThumb: String,

    var strSportThumbGreen: String,

    var strSportDescription: String,

    var isFavorite: Boolean
): Parcelable