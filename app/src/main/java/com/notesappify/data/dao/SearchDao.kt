package com.notesappify.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.notesappify.data.models.Notes
import com.notesappify.data.models.Search
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertQuery(query: Search)

    @Query("select * from searches order by registerTime DESC LIMIT 5")
    fun getLatestSearches(): Flow<List<Search>>
}