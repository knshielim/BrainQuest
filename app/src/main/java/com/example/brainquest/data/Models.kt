package com.example.brainquest.data

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes

data class Subject(
    val id: String,
    val name: String,
    val description: String,
    @param:ColorRes val colorRes: Int,
    @param:ColorRes val lightColorRes: Int,
    @param:DrawableRes val iconRes: Int,
    val lessons: List<Lesson>,
    val questions: List<Question>
)

data class Lesson(
    val id: String,
    val subjectId: String,
    val title: String,
    val summary: String,
    val content: String,
    val keyPoints: List<String>,
    @param:DrawableRes val imageRes: Int,
    val videoUrl: String,
    val practice: Question
)

data class Question(
    val text: String,
    val options: List<String>,
    val answerIndex: Int,
    val explanation: String
)

data class Badge(
    val id: String,
    val emoji: String,
    val title: String,
    val description: String,
    val unlocked: Boolean
)

data class QuizRecord(
    val subjectId: String,
    val percent: Int,
    val points: Int,
    val stars: Int,
    val timestamp: Long
)
