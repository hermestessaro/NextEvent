package com.hermes.nextevent.data.remote.model

import java.text.SimpleDateFormat
import java.util.Locale

data class Event(
    val people: List<String?>,
    val date: Long,
    val description: String,
    val image: String,
    val longitude: Double,
    val latitude: Double,
    val price: Float,
    val title: String,
    val id: String
) {
    fun getFormattedDate(): String {
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
        return sdf.format(date)
    }
}