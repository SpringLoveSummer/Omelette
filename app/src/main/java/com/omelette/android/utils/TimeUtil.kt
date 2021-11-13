package com.omelette.android.utils

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object TimeUtil {
    private const val minute = 60 * 1000 // 1分钟
        .toLong()
    private const val hour = 60 * minute // 1小时
    private const val day = 24 * hour // 1天
    private const val month = 31 * day // 月
    private const val year = 12 * month // 年

    //String时间转long
    //strTime要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日
    @SuppressLint("SimpleDateFormat")
    @Throws(ParseException::class)
    fun stringToDate(strTime: String, formatType: String): Date = SimpleDateFormat(formatType).parse(strTime)


    /**
     *
     * @param date 传入的时间
     * @return
     */
    @Throws(ParseException::class)
    fun getTimeFormatText(date: Date): String {
        val diff = Date().time - date.time
        return when {
            diff > year -> "${diff / year}年前"
            diff > month -> "${diff / month}月前"
            diff > day -> "${diff / day}天前"
            diff > hour -> "${diff / hour}小时前"
            diff > minute -> "${diff / minute}分钟前"
            else -> "刚刚"
        }
    }
}