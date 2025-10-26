package com.example.practice3.news.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class FilmsDbEntity (
    @PrimaryKey val id: String,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "text") val text: String?,
    @ColumnInfo(name = "year") val year: String,
    @ColumnInfo(name = "imageUrl") val imageUrl: String?,
)