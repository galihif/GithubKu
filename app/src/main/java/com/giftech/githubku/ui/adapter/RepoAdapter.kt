package com.giftech.githubku.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.giftech.githubku.data.model.Repo
import com.giftech.githubku.databinding.ItemRepoBinding

class RepoAdapter : ListAdapter<Repo, RepoAdapter.RepoViewHolder>(DIFF_CALLBACK) {

    inner class RepoViewHolder(private val binding: ItemRepoBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: Repo){
            binding.tvTitle.text = item.title
            binding.tvDesc.text = item.desc
            binding.tvStar.text = "${item.star}"
            binding.tvUpdated.text = item.updated
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val binding =
            ItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val item = getItem(position)
        if(item!=null){
            holder.bind(item)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Repo>() {
            override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return oldItem.title == newItem.title
            }
        }
    }
}