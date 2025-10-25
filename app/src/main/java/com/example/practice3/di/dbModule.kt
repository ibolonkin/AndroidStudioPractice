package com.example.practice3.di

import android.content.Context
import androidx.room.Room
import com.example.practice3.news.data.db.FilmsDatabase
import org.koin.dsl.module

val dbModule = module {
    single { DatabaseBuilder.getInstance(get()) }
}

object DatabaseBuilder {
    fun getInstance(context: Context) = buildRoomDB(context)

    private fun buildRoomDB(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            FilmsDatabase::class.java,
            "films"
        ).build()
}