package com.example.mynotes.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object Extensions{

    fun String.checkStringIsNotNullOrEmpty() = isEmpty()

    @SuppressLint("SimpleDateFormat")
    fun Long.dateFormat():String? = SimpleDateFormat("MMM dd, HH:mm:ss").format(Date(this))
}