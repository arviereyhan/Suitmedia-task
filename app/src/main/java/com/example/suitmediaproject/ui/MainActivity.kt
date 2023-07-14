package com.example.suitmediaproject.ui

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import com.example.suitmediaproject.Preferences
import com.example.suitmediaproject.UserModel
import com.example.suitmediaproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var userModel: UserModel
    private lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
        preferences = Preferences(this)
        checked()
        toWelcome()

    }

    private fun checked(){
        binding.btnCheck.setOnClickListener {
            val palindrome = binding.palindromeEditText.text.toString()
            if (palindrome == ""){
                Toast.makeText(this, "input text first", Toast.LENGTH_SHORT).show()
            } else if (palindromeCheck(palindrome)){
                Toast.makeText(this, "isPalindrome", Toast.LENGTH_SHORT).show()
                Log.d("palindrome", palindrome)
            }
            else{
                Toast.makeText(this, "not palindrome", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun toWelcome(){
        binding.btnNext.setOnClickListener {
            val name = binding.usernameEditText.text.toString()
            if (name != ""){
                val loginModel = UserModel(
                    Name = name
                )
                preferences.saveUser(loginModel)
                val intent = Intent(this, WelcomeActivity::class.java )
                startActivity(intent)
            } else if (name == ""){
                Toast.makeText(this, "put username first", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun palindromeCheck(text: String): Boolean {
        val reverseString = text.reversed()
        return text.equals(reverseString, ignoreCase = true)
    }

    private fun setupView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    companion object{
        const val EXTRA_NAME = "extra_name"
    }
}