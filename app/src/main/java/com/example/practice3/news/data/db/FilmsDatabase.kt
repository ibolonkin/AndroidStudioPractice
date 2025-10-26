package com.example.practice3.news.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.practice3.news.data.dao.FilmsDao
import com.example.practice3.news.data.entity.FilmsDbEntity

@Database(entities = [FilmsDbEntity::class], version = 1)
abstract class FilmsDatabase : RoomDatabase() {
    abstract fun filmsDao(): FilmsDao
}