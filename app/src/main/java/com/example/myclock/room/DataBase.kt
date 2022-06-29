package com.example.myclock.room

import android.annotation.SuppressLint
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Clock::class, Habit::class, HistoryClock::class, ExtendInfo::class], version = 1, exportSchema = false)
abstract class DataBase: RoomDatabase() {
    abstract fun clockDao(): ClockDao
    abstract fun historyClockDao(): HistoryClockDao
}

@SuppressLint("StaticFieldLeak")
object DB {

    private lateinit var db: DataBase

    fun init(context: Context) {
        db = Room.databaseBuilder(
          context,
          DataBase::class.java, "database-name"
        ).build()
    }

    val database get() = db

}