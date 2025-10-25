package com.example.practice3.news.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.practice3.news.data.entity.FilmsDbEntity

@Dao
interface FilmsDao {
    @Query("SELECT * FROM FilmsDbEntity")
    suspend fun getAll(): List<FilmsDbEntity>

    @Insert
    suspend fun insert(driverDbEntity: FilmsDbEntity)
}