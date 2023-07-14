package com.example.suitmediaproject.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.suitmediaproject.R
import com.example.suitmediaproject.ViewModelFactory
import com.example.suitmediaproject.data.remote.response.DataItem
import com.example.suitmediaproject.databinding.ActivityListUserBinding
import com.example.suitmediaproject.Result

class ListUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListUserBinding
    private lateinit var viewModel: ListUserViewModel
    private lateinit var layoutManager: LinearLayoutManager
    private var isFirstLoad = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Third Screen"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val viewModelFactory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(
            this@ListUserActivity,
            viewModelFactory
        )[ListUserViewModel::class.java]
        layoutManager = LinearLayoutManager(this@ListUserActivity)
        binding.rvUser.layoutManager = layoutManager
        binding.rvUser.setHasFixedSize(true)
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvUser.addItemDecoration(itemDecoration)

        isFirstLoad = true
        getAllUser()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed() // Kembali ke HomeFragment
                return true
            }
        }
        return super.onContextItemSelected(item)
    }

    private fun getAllUser() {
        viewModel.getUser().observe(this){
            when (it){
                is Result.Success -> {
                    val adapter = UserAdapter(it.data.data)
                    Log.d("test",it.data.data.toString())
                    binding.rvUser.adapter = adapter
                    adapter.setOnItemClickListener(object : UserAdapter.OnItemClickListener{
                        override fun onItemClick(data: DataItem) {
                            val intent = Intent(this@ListUserActivity, WelcomeActivity::class.java)
                            intent.putExtra(WelcomeActivity.EXTRA_SELECTED, data.firstName + " " + data.lastName)
                            startActivity(intent)
                            finish()
                        }
                    })
                }
                is Result.Error -> {
                    Toast.makeText(this, it.error, Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}