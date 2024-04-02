package com.example.nbktask.utils

import java.time.Duration
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

fun categorizeTime(timestamp: String): String {
    if(timestamp.isNullOrEmpty() || timestamp=="null")
         return "";
    val formatter = DateTimeFormatter.ISO_INSTANT
    val dateTime = Instant.parse(timestamp)
    val zoneId = ZoneId.of("UTC") // Assuming the timestamp is in UTC timezone

    val zonedDateTime = ZonedDateTime.ofInstant(dateTime, zoneId)
    val currentTime = ZonedDateTime.now(zoneId)

    val duration = Duration.between(zonedDateTime, currentTime)
    val hours = duration.toHours()
    val days = duration.toDays()
    val weeks = days / 7
    val months = days / 30
    val years = months / 12

    return when {
        duration.seconds < 3600 -> "Less than an hour ago"
        hours < 24 -> "${hours}h ago"
        days < 7 -> "${days}d ago"
        weeks < 4 -> "${weeks}w ago"
        months < 12 -> "${months}m ago"
        else -> "${years}y ago"
    }
}