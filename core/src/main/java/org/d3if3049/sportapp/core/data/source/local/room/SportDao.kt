package org.d3if3049.sportapp.core.data.source.local.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import org.d3if3049.sportapp.core.data.source.local.entity.SportEntity

@Dao
interface SportDao {
    @Query("select * from sport")
    fun getAllSport(): Flow<List<SportEntity>>

    @Query("select * from sport where isFavorite = 1")
    fun getFavoriteSport(): Flow<List<SportEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSport(tourism: List<SportEntity>)

    @Update
    fun updateFavoriteTourism(tourism: SportEntity)
}