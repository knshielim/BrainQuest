package com.example.brainquest.data

import android.content.Context
import android.content.SharedPreferences
import android.util.Patterns
import androidx.core.content.edit
import java.security.MessageDigest
import java.security.SecureRandom

class AuthManager(context: Context) {

    private val prefs: SharedPreferences =
        context.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    val currentEmail: String? get() = prefs.getString(KEY_CURRENT, null)

    val isLoggedIn: Boolean get() = currentEmail != null

    fun nameFor(email: String): String = prefs.getString(KEY_NAME + normalise(email), "").orEmpty()

    fun accountExists(email: String): Boolean = prefs.contains(KEY_PASSWORD + normalise(email))

    // Creates an account and log in
    fun signUp(name: String, email: String, password: String, confirm: String): String? {
        val mail = normalise(email)
        val error = when {
            name.isBlank() -> "Please enter your name"
            !Patterns.EMAIL_ADDRESS.matcher(mail).matches() -> "Please enter a valid e-mail"
            accountExists(mail) -> "An account with this e-mail already exists"
            password.length < MIN_PASSWORD -> "Password must be at least $MIN_PASSWORD characters"
            password != confirm -> "Passwords do not match"
            else -> null
        }
        if (error != null) return error

        val salt = newSalt()
        prefs.edit {
            putString(KEY_NAME + mail, name.trim())
            putString(KEY_PASSWORD + mail, salt + ":" + hash(salt, password))
            putString(KEY_CURRENT, mail)
        }
        return null
    }

    // Logs in if the e-mail and password match
    fun login(email: String, password: String): String? {
        val mail = normalise(email)
        if (mail.isEmpty() || password.isEmpty()) return "Please enter your e-mail and password"
        val saved = prefs.getString(KEY_PASSWORD + mail, null) ?: return "No account found for this e-mail"
        val salt = saved.substringBefore(":")
        if (hash(salt, password) != saved.substringAfter(":")) return "Wrong password, please try again"
        prefs.edit { putString(KEY_CURRENT, mail) }
        return null
    }

    fun logout() {
        prefs.edit { remove(KEY_CURRENT) }
    }

    private fun normalise(email: String) = email.trim().lowercase()

    private fun newSalt(): String {
        val bytes = ByteArray(16)
        SecureRandom().nextBytes(bytes)
        return bytes.toHex()
    }

    private fun hash(salt: String, password: String): String =
        MessageDigest.getInstance("SHA-256").digest((salt + password).toByteArray()).toHex()

    private fun ByteArray.toHex() = joinToString("") { "%02x".format(it) }

    companion object {
        private const val PREFS_NAME = "brainquest_accounts"
        private const val KEY_CURRENT = "current_user"
        private const val KEY_NAME = "name_"
        private const val KEY_PASSWORD = "password_"
        const val MIN_PASSWORD = 6

        // Name of the progress file of the user
        fun progressFileFor(context: Context): String {
            val email = AuthManager(context).currentEmail ?: return "brainquest_prefs"
            return "brainquest_prefs_" + email.replace(Regex("[^a-z0-9]"), "_")
        }
    }
}