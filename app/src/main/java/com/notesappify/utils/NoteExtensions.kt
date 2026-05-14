package com.notesappify.utils

import com.notesappify.data.Notes
import java.util.concurrent.TimeUnit

fun Notes.timeAgo(): String {
    val now = System.currentTimeMillis()
    val diff = now - this.registerTime

    return when {
        diff < TimeUnit.MINUTES.toMillis(1) -> {
            val seconds = TimeUnit.MILLISECONDS.toSeconds(diff)
            if (seconds <= 1) "Hace 1 segundo" else "Hace $seconds segundos"
        }

        diff < TimeUnit.HOURS.toMillis(1) -> {
            val minutes = TimeUnit.MILLISECONDS.toMinutes(diff)
            if (minutes == 1L) "Hace 1 minuto" else "Hace $minutes minutos"
        }

        diff < TimeUnit.DAYS.toMillis(1) -> {
            val hours = TimeUnit.MILLISECONDS.toHours(diff)
            if (hours == 1L) "Hace 1 hora" else "Hace $hours horas"
        }

        diff < TimeUnit.DAYS.toMillis(7) -> {
            val days = TimeUnit.MILLISECONDS.toDays(diff)
            if (days == 1L) "Hace 1 día" else "Hace $days días"
        }

        diff < TimeUnit.DAYS.toMillis(30) -> {
            val weeks = TimeUnit.MILLISECONDS.toDays(diff) / 7
            if (weeks == 1L) "Hace 1 semana" else "Hace $weeks semanas"
        }

        diff < TimeUnit.DAYS.toMillis(365) -> {
            val months = TimeUnit.MILLISECONDS.toDays(diff) / 30
            if (months == 1L) "Hace 1 mes" else "Hace $months meses"
        }

        else -> {
            val years = TimeUnit.MILLISECONDS.toDays(diff) / 365
            if (years == 1L) "Hace 1 año" else "Hace $years años"
        }
    }
}