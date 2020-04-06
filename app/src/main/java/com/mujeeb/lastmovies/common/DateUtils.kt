package com.mujeeb.lastmovies.common


import java.text.SimpleDateFormat
import java.util.*


class DateUtils {
    companion object {
        fun changeDateFormat(oldDate: String): String {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val date: Date = dateFormat.parse(oldDate)!!
            dateFormat.applyPattern("dd/MM/yyyy")
            return dateFormat.format(date)
        }

    }
}