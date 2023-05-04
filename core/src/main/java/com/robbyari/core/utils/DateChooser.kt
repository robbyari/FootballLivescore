package com.robbyari.core.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

object DateChooser {

    fun getToDate(): String {
        val timeZone = TimeZone.getDefault().id
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        dateFormat.timeZone = TimeZone.getTimeZone(timeZone)
        return dateFormat.format(Date())
    }

    fun getFromDate(): String {
        val timeZone = TimeZone.getDefault().id
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        dateFormat.timeZone = TimeZone.getTimeZone(timeZone)

        val calendar = Calendar.getInstance(TimeZone.getTimeZone(timeZone))
        calendar.add(Calendar.DAY_OF_YEAR, -7)
        return dateFormat.format(calendar.time)
    }

}