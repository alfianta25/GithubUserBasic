package com.yoga.githubusertest.feature.userdetail.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yoga.githubusertest.common.constant.Config
import com.yoga.githubusertest.common.constant.Config.Constant.letGetTimeAgo
import com.yoga.githubusertest.databinding.RepoItemBinding
import com.yoga.githubusertest.feature.userdetail.model.RepoResponseItem

class ReposAdapter: RecyclerView.Adapter<ReposAdapter.MyViewHolder>() {
    inner class MyViewHolder(val binding:RepoItemBinding):
        RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<RepoResponseItem>(){
        override fun areItemsTheSame(
            oldItem: RepoResponseItem,
            newItem: RepoResponseItem
        ): Boolean {
            return oldItem.id ==newItem.id
        }

        override fun areContentsTheSame(
            oldItem: RepoResponseItem,
            newItem: RepoResponseItem
        ): Boolean {
            return newItem == oldItem
        }
    }

    private val differ = AsyncListDiffer(this,diffCallback)
    var repos:List<RepoResponseItem>
        get()= differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            RepoItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var currentRepos = repos[position]

        holder.binding.apply {
            tvTitle.text = currentRepos.name
            tvDesc.text = currentRepos.description
            tvFavorite.text = currentRepos.stargazers_count.toString()
            tvTimeago.text = "Updated"+ Config.Constant.convertDate(currentRepos.created_at).letGetTimeAgo()

            Glide.with(thumbImage)
                .load(currentRepos.owner.avatar_url)
                .into(thumbImage)
        }

    }

    override fun getItemCount(): Int {
        return repos.size
    }

}