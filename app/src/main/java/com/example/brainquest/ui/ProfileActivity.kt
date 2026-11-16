package com.example.brainquest.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.brainquest.MainActivity
import com.example.brainquest.R
import com.example.brainquest.data.AuthManager
import com.example.brainquest.data.ProgressManager
import com.example.brainquest.databinding.ActivityProfileBinding
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var progress: ProgressManager
    private var firstRun = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableBrainQuestEdgeToEdge()
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        applySystemBarInsets(binding.main, binding.appBarMain.root)

        progress = ProgressManager(this)
        firstRun = intent.getBooleanExtra(Extras.FIRST_RUN, false) || !progress.hasProfile()

        setSupportActionBar(binding.appBarMain.toolbar)
        supportActionBar?.title = getString(if (firstRun) R.string.title_create_profile else R.string.title_edit_profile)
        supportActionBar?.setDisplayHomeAsUpEnabled(!firstRun)

        val currentAvatar = if (firstRun) ProgressManager.AVATARS.first() else progress.avatar
        val currentForm = if (firstRun) ProgressManager.FORMS.first() else progress.form

        addChoiceChips(binding.chipGroupAvatar, ProgressManager.AVATARS, currentAvatar, textSize = 24f)
        addChoiceChips(binding.chipGroupForm, ProgressManager.FORMS, currentForm, textSize = 15f)
        binding.textAvatar.text = currentAvatar
        binding.chipGroupAvatar.setOnCheckedStateChangeListener { group, _ ->
            binding.textAvatar.text = selectedText(group) ?: currentAvatar
        }

        if (firstRun) {
            binding.buttonSave.setText(R.string.btn_start_learning)
            val auth = AuthManager(this)
            auth.currentEmail?.let { binding.editName.setText(auth.nameFor(it)) }
        } else {
            binding.textWelcome.text = progress.name
            binding.textWelcomeSub.text = "${progress.form} • ${progress.school}"
            binding.editName.setText(progress.name)
            binding.editSchool.setText(progress.school)
            binding.buttonSave.setText(R.string.btn_save_profile)
            showStats()
        }

        binding.buttonSave.setOnClickListener { saveProfile() }
    }

    private fun showStats() {
        binding.cardStats.visibility = View.VISIBLE
        binding.textStatLevel.text = progress.level.toString()
        binding.textStatPoints.text = progress.totalPoints.toString()
        binding.textStatStars.text = progress.totalStars.toString()
        binding.textStatBadges.text = progress.unlockedBadgeIds().size.toString()
    }

    private fun addChoiceChips(group: ChipGroup, items: List<String>, selected: String, textSize: Float) {
        items.forEach { item ->
            val chip = layoutInflater.inflate(R.layout.item_chip_choice, group, false) as Chip
            chip.id = View.generateViewId()
            chip.text = item
            chip.textSize = textSize
            group.addView(chip)
            if (item == selected) chip.isChecked = true
        }
    }

    private fun selectedText(group: ChipGroup): String? =
        group.findViewById<Chip>(group.checkedChipId)?.text?.toString()

    private fun saveProfile() {
        val name = binding.editName.text?.toString()?.trim().orEmpty()
        val school = binding.editSchool.text?.toString()?.trim().orEmpty()

        binding.inputName.error = if (name.isEmpty()) getString(R.string.error_name) else null
        binding.inputSchool.error = if (school.isEmpty()) getString(R.string.error_school) else null
        if (name.isEmpty() || school.isEmpty()) return

        val avatar = selectedText(binding.chipGroupAvatar) ?: ProgressManager.AVATARS.first()
        val form = selectedText(binding.chipGroupForm) ?: ProgressManager.FORMS.first()
        progress.saveProfile(name, form, school, avatar)
        Toast.makeText(this, R.string.profile_saved, Toast.LENGTH_SHORT).show()

        if (firstRun) {
            // Explicit intent
            startActivity(Intent(this, MainActivity::class.java))
        }
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
