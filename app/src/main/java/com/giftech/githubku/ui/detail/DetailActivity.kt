package com.giftech.githubku.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.giftech.githubku.data.model.User
import com.giftech.githubku.databinding.ActivityDetailBinding
import com.giftech.githubku.ui.adapter.RepoAdapter
import com.giftech.githubku.utils.AppUtils.loadImage
import com.giftech.githubku.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private lateinit var binding:ActivityDetailBinding
    private val viewModel:DetailViewModel by viewModels()
    private lateinit var repoAdapter: RepoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupAdapter()
        getUsername()
        getDetailUser()
        getListRepo()
    }

    private fun getListRepo() {
        viewModel.listRepo.observe(this){
            when(it){
                is Resource.Error -> Log.d("TAG", "getListRepo: ${it.error}")
                Resource.Loading -> Log.d("TAG", "getListRepo: Loading")
                is Resource.Success -> repoAdapter.submitList(it.data)
            }
        }
    }

    private fun getDetailUser() {
        viewModel.user.observe(this){
            when(it){
                is Resource.Error -> Log.d("TAG", "getDetailUser: ${it.error}")
                Resource.Loading -> Log.d("TAG", "getDetailUser: Loading")
                is Resource.Success -> populateUserDetail(it.data)
            }
        }
    }

    private fun populateUserDetail(user: User) {
        with(binding){
            tvName.text = user.name
            tvUsername.text = "@${user.username}"
            tvBio.text = user.bio
            ivAvatar.loadImage(user.avatar)
        }
    }

    private fun getUsername() {
        if(intent.extras!=null){
            val extras = intent.extras
            val username = extras!!.getString(USERNAME)
            viewModel.setUsername(username!!)
        }
    }

    private fun setupAdapter() {
        repoAdapter = RepoAdapter()
        binding.rvRepo.adapter = repoAdapter
    }

    companion object{
        const val USERNAME = "USERNAME"
    }
}