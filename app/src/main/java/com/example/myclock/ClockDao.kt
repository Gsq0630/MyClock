package com.example.myclock

import androidx.room.*

@Dao
interface ClockDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg users: Clock)

    @Delete
    fun delete(clock: Clock)

    @Query("SELECT * FROM clock")
    fun getAll(): List<Clock>

    @Delete
    fun deleteClock(vararg users: Clock)

    @Query("SELECT * FROM clock WHERE habitId = :habitId order by priority")
    fun getClockByHabitId(habitId: Int): List<Clock>

}