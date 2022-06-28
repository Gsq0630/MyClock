package com.example.myclock

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

@Entity(tableName = "clock")
data class Clock(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0, //id
    val name: String = "", //名字
    val content: String = "", // 内容
    val shouldDate: String = "",//需打卡日期
    var state: Int = 0, //打卡状态
    val priority: Int = 1, //打卡优先级
    val timestamp: Long = System.currentTimeMillis(),//创建时间
    val habitId: Int = 1, //习惯id
)

@Entity(tableName = "habit")
data class Habit(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String = "",
    val timestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "history_clock")
data class HistoryClock(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,//id
    // 打卡名字
    val name: String = "",
    //打卡日期
    val date: Long = System.currentTimeMillis(),
    //打卡状态
    val state: Int = 1,
    //习惯id
    val habitId: Int = 1,
    //创建时间
    val timestamp: Long = System.currentTimeMillis()
)
