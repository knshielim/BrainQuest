package com.example.brainquest.data

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class ProgressManager(context: Context) {

    private val prefs: SharedPreferences =
        context.applicationContext.getSharedPreferences(AuthManager.progressFileFor(context), Context.MODE_PRIVATE)

    // Student profile
    fun hasProfile(): Boolean = prefs.contains(KEY_NAME)

    val name: String get() = prefs.getString(KEY_NAME, "") ?: ""
    val form: String get() = prefs.getString(KEY_FORM, FORMS.first()) ?: FORMS.first()
    val school: String get() = prefs.getString(KEY_SCHOOL, "") ?: ""
    val avatar: String get() = prefs.getString(KEY_AVATAR, AVATARS.first()) ?: AVATARS.first()

    fun saveProfile(name: String, form: String, school: String, avatar: String) {
        prefs.edit {
            putString(KEY_NAME, name)
            putString(KEY_FORM, form)
            putString(KEY_SCHOOL, school)
            putString(KEY_AVATAR, avatar)
        }
    }

    val firstName: String get() = name.trim().split(" ").firstOrNull().orEmpty()

    // Points & level
    val totalPoints: Int get() = prefs.getInt(KEY_POINTS, 0)

    fun addPoints(points: Int) {
        prefs.edit { putInt(KEY_POINTS, totalPoints + points) }
    }

    val level: Int get() = totalPoints / POINTS_PER_LEVEL + 1

    /** Points earned inside the current level (0 until POINTS_PER_LEVEL). */
    val pointsInLevel: Int get() = totalPoints % POINTS_PER_LEVEL

    val levelTitle: String
        get() = when (level) {
            1 -> "Beginner"
            2 -> "Explorer"
            3 -> "Achiever"
            4 -> "Scholar"
            else -> "Master"
        }

    // Lessons
    fun completedLessons(): Set<String> =
        HashSet(prefs.getStringSet(KEY_LESSONS, emptySet()) ?: emptySet())

    fun isLessonCompleted(lessonId: String): Boolean = lessonId in completedLessons()

    fun markLessonCompleted(lessonId: String): Boolean {
        val set = completedLessons().toMutableSet()
        if (!set.add(lessonId)) return false
        prefs.edit { putStringSet(KEY_LESSONS, set) }
        return true
    }

    fun completedLessonCount(subject: Subject): Int {
        val done = completedLessons()
        return subject.lessons.count { it.id in done }
    }

    // Quizzes

    fun bestScore(subjectId: String): Int = prefs.getInt(KEY_BEST + subjectId, -1)

    fun bestStars(subjectId: String): Int = prefs.getInt(KEY_STARS + subjectId, 0)

    fun attempts(subjectId: String): Int = prefs.getInt(KEY_ATTEMPTS + subjectId, 0)

    val quizzesTaken: Int get() = prefs.getInt(KEY_QUIZZES, 0)

    val totalStars: Int get() = LearningRepository.subjects.sumOf { bestStars(it.id) }


    val averageScore: Int
        get() {
            val answered = prefs.getInt(KEY_ANSWERED, 0)
            return if (answered == 0) 0 else prefs.getInt(KEY_CORRECT, 0) * 100 / answered
        }

    fun recordQuiz(subjectId: String, correct: Int, total: Int, points: Int): QuizRecord {
        val percent = if (total == 0) 0 else correct * 100 / total
        val stars = starsFor(percent)
        val record = QuizRecord(subjectId, percent, points, stars, System.currentTimeMillis())

        val newHistory = (listOf(record) + history()).take(MAX_HISTORY)
        prefs.edit {
            putInt(KEY_BEST + subjectId, maxOf(percent, bestScore(subjectId)))
            putInt(KEY_STARS + subjectId, maxOf(stars, bestStars(subjectId)))
            putInt(KEY_ATTEMPTS + subjectId, attempts(subjectId) + 1)
            putInt(KEY_QUIZZES, quizzesTaken + 1)
            putInt(KEY_CORRECT, prefs.getInt(KEY_CORRECT, 0) + correct)
            putInt(KEY_ANSWERED, prefs.getInt(KEY_ANSWERED, 0) + total)
            putInt(KEY_POINTS, totalPoints + points)
            putString(KEY_HISTORY, newHistory.joinToString(";") {
                "${it.subjectId}|${it.percent}|${it.points}|${it.stars}|${it.timestamp}"
            })
        }
        return record
    }

    fun history(): List<QuizRecord> {
        val raw = prefs.getString(KEY_HISTORY, "").orEmpty()
        if (raw.isBlank()) return emptyList()
        return raw.split(";").mapNotNull { item ->
            val p = item.split("|")
            if (p.size != 5) null
            else QuizRecord(p[0], p[1].toIntOrNull() ?: 0, p[2].toIntOrNull() ?: 0, p[3].toIntOrNull() ?: 0, p[4].toLongOrNull() ?: 0L)
        }
    }

    // Badges & certificate
    private fun mastered(vararg subjectIds: String): Boolean = subjectIds.all { bestScore(it) >= MASTERY }

    fun badges(): List<Badge> {
        val allIds = LearningRepository.subjects.map { it.id }.toTypedArray()
        val subjectsTried = LearningRepository.subjects.count { attempts(it.id) > 0 }
        return listOf(
            Badge("first_quiz", "🎯", "First Steps", "Complete your first quiz", quizzesTaken >= 1),
            Badge("explorer", "🧭", "Explorer", "Take a quiz in 6 different subjects", subjectsTried >= 6),
            Badge("bookworm", "📚", "Bookworm", "Complete all ${LearningRepository.totalLessons} lessons",
                completedLessons().size >= LearningRepository.totalLessons),
            Badge("perfect", "💯", "Perfectionist", "Get 100% in any quiz",
                LearningRepository.subjects.any { bestScore(it.id) == 100 }),
            Badge("points_1000", "💰", "Point Collector", "Earn 1000 points", totalPoints >= 1000),
            Badge("math_whiz", "🧮", "Math Whiz", "Score $MASTERY% or more in Mathematics", mastered("math")),
            Badge("science_star", "🔬", "Science Star", "Score $MASTERY% or more in Biology, Chemistry and Physics",
                mastered("biology", "chemistry", "physics")),
            Badge("tech_wizard", "💻", "Tech Wizard", "Score $MASTERY% or more in Computer Science and ICT",
                mastered("cs", "ict")),
            Badge("word_master", "✍️", "Word Master", "Score $MASTERY% or more in English", mastered("english")),
            Badge("business_brain", "📈", "Business Brain", "Score $MASTERY% or more in Business, Economics and Accounting",
                mastered("business", "economics", "accounting")),
            Badge("humanities_hero", "🌍", "Humanities Hero", "Score $MASTERY% or more in Geography and History",
                mastered("geography", "history")),
            Badge("champion", "🏆", "BrainQuest Champion",
                "Score $MASTERY% or more in all ${LearningRepository.subjects.size} subjects", mastered(*allIds))
        )
    }

    fun unlockedBadgeIds(): Set<String> = badges().filter { it.unlocked }.map { it.id }.toSet()

    fun hasCertificate(): Boolean = unlockedBadgeIds().contains("champion")

    // Resets progress
    fun resetProgress() {
        val keep = mapOf(KEY_NAME to name, KEY_FORM to form, KEY_SCHOOL to school, KEY_AVATAR to avatar)
        prefs.edit {
            clear()
            keep.forEach { (k, v) -> putString(k, v) }
        }
    }

    companion object {
        private const val KEY_NAME = "name"
        private const val KEY_FORM = "form"
        private const val KEY_SCHOOL = "school"
        private const val KEY_AVATAR = "avatar"
        private const val KEY_POINTS = "points"
        private const val KEY_LESSONS = "lessons_done"
        private const val KEY_BEST = "best_"
        private const val KEY_STARS = "stars_"
        private const val KEY_ATTEMPTS = "attempts_"
        private const val KEY_QUIZZES = "quizzes_taken"
        private const val KEY_CORRECT = "total_correct"
        private const val KEY_ANSWERED = "total_answered"
        private const val KEY_HISTORY = "history"
        private const val MAX_HISTORY = 20

        const val POINTS_PER_LEVEL = 200
        const val MASTERY = 80
        const val LESSON_POINTS = 5

        val AVATARS = listOf("🦊", "🐼", "🦉", "🐯", "🐬", "🚀", "🎧", "⚽")
        val FORMS = listOf("Year 9", "Year 10", "Year 11")

        fun starsFor(percent: Int): Int = when {
            percent >= 90 -> 3
            percent >= 70 -> 2
            percent >= 50 -> 1
            else -> 0
        }
    }
}
