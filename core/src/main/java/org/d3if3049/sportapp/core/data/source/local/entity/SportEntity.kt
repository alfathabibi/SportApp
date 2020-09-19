package org.d3if3049.sportapp.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sport")
data class SportEntity (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "idSport")
    var idSport: String,

    @ColumnInfo(name = "strSport")
    var strSport: String,

    @ColumnInfo(name = "strFormat")
    var strFormat: String,

    @ColumnInfo(name = "strSportThumb")
    var strSportThumb: String,

    @ColumnInfo(name = "strSportThumbGreen")
    var strSportThumbGreen: String,

    @ColumnInfo(name = "strSportDescription")
    var strSportDescription: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)