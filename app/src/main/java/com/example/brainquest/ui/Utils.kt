package com.example.brainquest.ui

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.example.brainquest.R

object Extras {
    const val SUBJECT_ID = "extra_subject_id"
    const val LESSON_ID = "extra_lesson_id"
    const val FIRST_RUN = "extra_first_run"
    const val CORRECT = "extra_correct"
    const val TOTAL = "extra_total"
    const val POINTS = "extra_points"
    const val BEST_STREAK = "extra_best_streak"
}

// Implicit intent
fun Context.openUrl(url: String) {
    val implicitIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    try {
        startActivity(implicitIntent)
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(this, R.string.no_app_found, Toast.LENGTH_SHORT).show()
    }
}

// Implicit intent
fun Context.shareText(subject: String, text: String) {
    val implicitIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, text)
    }
    startActivity(Intent.createChooser(implicitIntent, "Share via"))
}

// Implicit intent
fun Context.sendEmail(address: String, subject: String, body: String) {
    val implicitIntent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:")
        putExtra(Intent.EXTRA_EMAIL, arrayOf(address))
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, body)
    }
    try {
        startActivity(implicitIntent)
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(this, R.string.no_app_found, Toast.LENGTH_SHORT).show()
    }
}

// Stars text
fun starText(stars: Int): String = "★".repeat(stars) + "☆".repeat(3 - stars)
