package com.lfp2.bulletjournal

data class Task(
    val name: String? = "",
    val notificationEnabled: Boolean ?= false,
    val notificationHour: String? = "",
    val timeSpent: String? = "",
    val done: Boolean ?= false,
    val uuid: String?= "")