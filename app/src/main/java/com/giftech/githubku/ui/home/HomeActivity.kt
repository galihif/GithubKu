package com.giftech.githubku.ui.home

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.giftech.githubku.databinding.ActivityHomeBinding
import com.giftech.githubku.ui.adapter.UserAdapter
import com.giftech.githubku.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val viewModel:HomeViewModel by viewModels()
    private lateinit var userAdapter: UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupAdapter()
        getUsers()
    }

    private fun getUsers() {
        viewModel.users.observe(this){
            when(it){
                is Resource.Error -> {
                    Toast.makeText(this, it.error, Toast.LENGTH_SHORT).show()
                }
                Resource.Loading -> {
                    Log.d("TAG", "getUsers: Loading")
                }
                is Resource.Success -> {
                    userAdapter.submitList(it.data)
                    Log.d("TAG", "getUsers: ${it.data.size}")
                }
            }
        }
    }

    private fun setupAdapter() {
        userAdapter = UserAdapter {

        }
        binding.rvUsers.adapter = userAdapter
    }
}