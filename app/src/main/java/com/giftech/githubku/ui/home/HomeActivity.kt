package com.giftech.githubku.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.giftech.githubku.databinding.ActivityHomeBinding
import com.giftech.githubku.ui.adapter.UserAdapter
import com.giftech.githubku.ui.detail.DetailActivity
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
            showEmpty()
            showError()
            showLoading()
            when(it){
                is Resource.Error -> {
                    showError(true,it.error)
                }
                Resource.Loading -> {
                    showLoading(true)
                }
                is Resource.Success -> {
                    showEmpty(it.data.isEmpty())
                    userAdapter.submitList(it.data)
                }
            }
        }
    }

    private fun showEmpty(empty: Boolean=false) {
        binding.empty.visibility = if (empty) View.VISIBLE else View.GONE
    }

    private fun showError(error: Boolean=false, errorMessage:String="") {
        binding.error.visibility = if (error) View.VISIBLE else View.GONE
        binding.errorMessage.text = errorMessage
    }

    private fun showLoading(loading: Boolean=false) {
        binding.loading.visibility = if (loading) View.VISIBLE else View.GONE
    }

    private fun setupAdapter() {
        userAdapter = UserAdapter {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.USERNAME, it.username)
            startActivity(intent)
        }
        binding.rvUsers.adapter = userAdapter
    }
}