package com.example.myclock.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HistoryClockDao {


  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertAll(vararg users: HistoryClock)

  @Delete
  fun delete(clock: HistoryClock)

  @Query("DELETE FROM history_clock WHERE clockId = :id AND date = :date")
  fun deleteByClockId(id: Int, date: String)

  @Query("SELECT * FROM history_clock ORDER BY timestamp DESC")
  fun getAll(): List<HistoryClock>

  @Delete
  fun deleteClock(vararg users: HistoryClock)

  @Query("SELECT * FROM history_clock WHERE clockId = :clockId AND date = :date LIMIT 1")
  fun selectHistory(clockId: Int, date: String): HistoryClock?

  @Query("DELETE FROM history_clock WHERE clockId = :clockId")
  fun deleteClockById(clockId: Int)


}