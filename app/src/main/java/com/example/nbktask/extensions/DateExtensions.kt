package com.example.nbktask.extensions

import android.app.DatePickerDialog
import android.content.Context
import com.google.android.material.textfield.TextInputEditText
import java.util.Calendar

fun Context.showDatePickerDialog(editText: TextInputEditText?) {
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

    val datePickerDialog = DatePickerDialog(this, { view, year, month, dayOfMonth ->
        val selectedDate = "$year-${month + 1}-$dayOfMonth"
        editText?.setText(selectedDate)
    }, year, month, dayOfMonth)

    datePickerDialog.show()
}