package com.example.nbktask.extensions

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@BindingAdapter("format_text", requireAll = false)
fun TextView.formatText(text: String?) {
    this.text = text?.formatDateToTimeAgo()
}

fun String.formatDateToTimeAgo(): String {
    try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = inputFormat.parse(this)
        val currentTime = Calendar.getInstance().time

        val diffInMillis = currentTime.time - date.time
        val seconds = diffInMillis / 1000
        val minutes = seconds / 60
        val hours = minutes / 60
        val days = hours / 24
        val weeks = days / 7
        val months = days / 30
        val years = days / 365

        return (if (years == 0L) "" else "$years years") +
                (if (months == 0L) "" else "$months months") +
                (if (weeks % 4L == 0L) "" else ", ${weeks % 4L} weeks") +
                "${if (days % 7L == 0L) "" else ", ${days % 7L} days"} ago"
    } catch (e: Exception) {
        return ""
    }
}