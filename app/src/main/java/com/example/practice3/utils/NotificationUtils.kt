package com.example.practice3.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

fun createNotificationChannel(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(
            "FAVORITE_PAIR_CHANNEL",
            "Уведомления о парах",
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = "Уведомления о начале любимой пары"
        }

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}