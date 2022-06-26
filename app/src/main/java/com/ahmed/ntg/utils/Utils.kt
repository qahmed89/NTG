package com.ahmed.ntg.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Utils {


    @RequiresApi(Build.VERSION_CODES.O)
    fun getStartData(): String {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return current.format(formatter)
    }



    @RequiresApi(Build.VERSION_CODES.O)
    fun getEndData(): String {
        val current = LocalDateTime.now().minusDays(3)
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return current.format(formatter)
    }
}