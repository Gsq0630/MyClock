package com.example.myclock

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "clock")
data class Clock(
    @PrimaryKey
    val id: Int, //id
    val name: String, //名字
    val content: String, // 内容
    val shouldDate: String,//需打卡日期
    val state: Int, //打卡状态
    val priority: Int, //打卡优先级
    val timestamp: String,//创建时间
    val habitId: Int, //习惯id
)

@Entity(tableName = "habit")
data class Habit(
    @PrimaryKey
    val id: Int,
    val name: String,
    val timestamp: String
)

@Entity(tableName = "history_clock")
data class HistoryClock(
    @PrimaryKey
    val id: Int,//id
    // 打卡名字
    val name: String,
    //打卡日期
    val date: String,
    //打卡状态
    val state: Int,
    //习惯id
    val habitId: String,
    //创建时间
    val timestamp: String
)