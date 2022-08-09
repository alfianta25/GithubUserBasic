package com.yoga.githubusertest.common.constant

import android.annotation.SuppressLint
import java.text.DecimalFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.pow

object Config {
    object Constant {
        const val BASE_URL = "https://api.github.com/"

        private const val SECOND_MILLIS = 1000
        private const val MINUTE_MILLIS = 60 * SECOND_MILLIS
        private const val HOUR_MILLIS = 60 * MINUTE_MILLIS
        private const val DAY_MILLIS = 24 * HOUR_MILLIS

        private fun currentDate(): Date {
            val calendar = Calendar.getInstance()
            return calendar.time
        }

        fun convertDate(stringDate: String): Date? {
            val format = SimpleDateFormat("yyyy-MM-dd")
            try {
                val date: Date = format.parse(stringDate) as Date
                return date
            } catch (e: ParseException) {
                return null
                e.printStackTrace()
            }


        }
        fun Date?.letGetTimeAgo(): String {
            val calendar = Calendar.getInstance()
            calendar.time = this

            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            val currentCalendar = Calendar.getInstance()

            val currentYear = currentCalendar.get(Calendar.YEAR)
            val currentMonth = currentCalendar.get(Calendar.MONTH)
            val currentDay = currentCalendar.get(Calendar.DAY_OF_MONTH)
            val currentHour = currentCalendar.get(Calendar.HOUR_OF_DAY)
            val currentMinute = currentCalendar.get(Calendar.MINUTE)

            return if (year < currentYear ) {
                val interval = currentYear - year
                if (interval == 1) "$interval year ago" else "$interval years ago"
            } else if (month < currentMonth) {
                val interval = currentMonth - month
                if (interval == 1) "$interval month ago" else "$interval months ago"
            } else  if (day < currentDay) {
                val interval = currentDay - day
                if (interval == 1) "$interval day ago" else "$interval days ago"
            } else if (hour < currentHour) {
                val interval = currentHour - hour
                if (interval == 1) "$interval hour ago" else "$interval hours ago"
            } else if (minute < currentMinute) {
                val interval = currentMinute - minute
                if (interval == 1) "$interval minute ago" else "$interval minutes ago"
            } else {
                "a moment ago"
            }
        }

        fun countingConvert(count: Long): String? {

            val array = arrayOf(' ', 'k', 'M', 'B', 'T', 'P', 'E')
            val value = Math.floor(Math.log10(count.toDouble())).toInt()
            val  base = value / 3
            if (value >= 3 && base < array.size) {
                return DecimalFormat("#0.0").format(count/ 10.0.pow((base * 3).toDouble())) + array[base]
            } else {
                return DecimalFormat("#,##0").format(count)
            }
        }
        fun String?.replaceNull(): String {
            if (this == null) {
                return ""
            }else

                return this
        }



    }
}