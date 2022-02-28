package com.example.jetcomposedemo.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jetcomposedemo.model.Record
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Query("SELECT * FROM record")
    fun getAllData(): Flow<List<Record>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNewData(recorda: List<Record>)

    @Query("UPDATE record SET id = :id, volume = :volume, quarter = :quarter WHERE id =:id")
    fun updateData(id: Int, volume: Double, quarter: String)

    @Query("DELETE FROM record")
    fun deleteAll()
}