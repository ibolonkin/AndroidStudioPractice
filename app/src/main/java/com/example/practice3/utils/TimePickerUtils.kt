package com.example.practice3.utils

import android.app.TimePickerDialog
import android.content.Context
import java.util.Calendar

fun showTimePicker(
    context: Context,
    currentTime: String = "",
    onTimeSelected: (hour: Int, minute: Int) -> Unit
) {
    val calendar = Calendar.getInstance()

    if (currentTime.isNotEmpty() && currentTime.contains(":")) {
        try {
            val parts = currentTime.split(":")
            val hour = parts[0].toInt()
            val minute = parts[1].toInt()
            calendar.set(Calendar.HOUR_OF_DAY, hour)
            calendar.set(Calendar.MINUTE, minute)
        } catch (e: Exception) {
            val now = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, now.get(Calendar.HOUR_OF_DAY))
            calendar.set(Calendar.MINUTE, now.get(Calendar.MINUTE))
        }
    }

    val timePickerDialog = TimePickerDialog(
        context,
        { _, hour, minute ->
            onTimeSelected(hour, minute)
        },
        calendar.get(Calendar.HOUR_OF_DAY),
        calendar.get(Calendar.MINUTE),
        true
    )

    timePickerDialog.setTitle("Выберите время")
    timePickerDialog.show()
}