package com.example.mynotes.utils

import java.text.SimpleDateFormat
import java.util.*

object Extensions{

    fun String.checkStringIsNotNullOrEmpty() = isEmpty()
    fun Long.dateFormat():String? = SimpleDateFormat("mm dd yy, hh mm ss").format(Date(this))
}