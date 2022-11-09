package com.giftech.githubku.ui.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
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
        searchUser()
    }

    private fun searchUser() {
        binding.etSearch.setOnEditorActionListener(TextView.OnEditorActionListener { textView, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val keyword = textView.text.toString()
                viewModel.setQuery(keyword)
                hideKeyboard()
                return@OnEditorActionListener true
            }
            false
        })
    }

    private fun hideKeyboard() {
        binding.etSearch.clearFocus()
        val imn: InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imn.hideSoftInputFromWindow(binding.etSearch.windowToken, 0)
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