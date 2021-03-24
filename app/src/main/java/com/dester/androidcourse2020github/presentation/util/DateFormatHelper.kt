package com.dester.androidcourse2020github.presentation.util

import java.text.SimpleDateFormat
import java.util.*

enum class DateFormatHelper(private val format: String, private val utcFixed: Boolean = false) {

    SimpleTime("HH:mm");

    private val formatter = SimpleDateFormat(format, RUSSIA)

    init {
        if (utcFixed) {
            formatter.timeZone = TimeZone.getTimeZone("UTC")
        }
    }

    fun parse(string: String): Date? = formatter.parse(string)


    fun format(date: Date): String = formatter.format(date)

}

private val RUSSIA by lazy { Locale("ru", "RU") }