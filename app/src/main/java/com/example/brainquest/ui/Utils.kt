package com.example.brainquest.ui

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.view.View
import android.widget.Toast
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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

fun AppCompatActivity.enableBrainQuestEdgeToEdge() {
    enableEdgeToEdge(statusBarStyle = SystemBarStyle.dark(Color.TRANSPARENT))
}

fun applySystemBarInsets(root: View, appBar: View? = null) {
    ViewCompat.setOnApplyWindowInsetsListener(root) { v, insets ->
        val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.ime())
        if (appBar == null) {
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom)
        } else {
            v.setPadding(bars.left, 0, bars.right, bars.bottom)
            appBar.setPadding(0, bars.top, 0, 0)
        }
        WindowInsetsCompat.CONSUMED
    }
}

// Implicit intent
fun Context.openUrl(url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    try {
        startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(this, R.string.no_app_found, Toast.LENGTH_SHORT).show()
    }
}

// Implicit intent
fun Context.shareText(subject: String, text: String) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, text)
    }
    startActivity(Intent.createChooser(intent, "Share via"))
}

// Implicit intent
fun Context.sendEmail(address: String, subject: String, body: String) {
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:")
        putExtra(Intent.EXTRA_EMAIL, arrayOf(address))
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, body)
    }
    try {
        startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(this, R.string.no_app_found, Toast.LENGTH_SHORT).show()
    }
}

fun starText(stars: Int): String = "★".repeat(stars) + "☆".repeat(3 - stars)
