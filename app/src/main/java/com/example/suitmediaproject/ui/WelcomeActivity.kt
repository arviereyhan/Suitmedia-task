package com.example.suitmediaproject.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.example.suitmediaproject.Preferences
import com.example.suitmediaproject.UserModel
import com.example.suitmediaproject.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding
    private lateinit var userModel: UserModel
    private lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Second Screen"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        preferences = Preferences(this)
        userModel = preferences.getUser()
        val username = userModel.Name
        binding.tvUsername.text = username

        val selectedUser = intent.getStringExtra(EXTRA_SELECTED)
        Log.d("selectedUser", selectedUser.toString())
        if (selectedUser == null) {
            binding.tvSelected.text = "Selected User Name"
        } else {
            binding.tvSelected.text = selectedUser
        }

        binding.btnNext.setOnClickListener {
            val intent = Intent(this, ListUserActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onContextItemSelected(item)
    }

    companion object{
        const val EXTRA_SELECTED = "extra_selected"
    }

}