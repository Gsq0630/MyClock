package com.example.myclock.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.Date
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
    val clockId: Int = 0,
    // 打卡名字
    val name: String = "",
    //打卡日期
    var date: String = getDayDate(),
    //打卡状态
    var state: Int = 1,
    //习惯id
    val habitId: Int = 1,
    //创建时间
    val timestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "extend_info")
data class ExtendInfo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 1,
    var lateChangeDay: String = getDayDate() ,
    val info: String = ""
)

fun getDayDate(): String {
    val  formatter= SimpleDateFormat("yyyy-MM-dd");
    var date = Date(System.currentTimeMillis());
    return formatter.format(date)
}