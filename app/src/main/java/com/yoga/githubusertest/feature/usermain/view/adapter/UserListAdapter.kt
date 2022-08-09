package com.yoga.githubusertest.feature.usermain.view.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yoga.githubusertest.databinding.UsersItemBinding
import com.yoga.githubusertest.feature.userdetail.view.DetailUserActivity
import com.yoga.githubusertest.feature.usermain.model.UserListResponseItem
import com.yoga.githubusertest.feature.usermain.model.UsersItem

class UsersListAdapter: RecyclerView.Adapter<UsersListAdapter.MyViewHolder>() {
    inner class MyViewHolder(val binding: UsersItemBinding ):
        RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<UserListResponseItem>(){
        override fun areItemsTheSame(
            oldItem: UserListResponseItem,
            newItem: UserListResponseItem
        ): Boolean {
            return oldItem.id ==newItem.id
        }

        override fun areContentsTheSame(
            oldItem: UserListResponseItem,
            newItem: UserListResponseItem
        ): Boolean {
            return newItem == oldItem
        }
    }

    private val differ = AsyncListDiffer(this,diffCallback)
    var users:List<UserListResponseItem>
        get()= differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(UsersItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var currentUsers = users[position]


        holder.binding.apply {
            tvUserName.text =currentUsers.login
            tvSocialMedia.text = "@"+currentUsers.login
            tvEmail.text = currentUsers.login+"@gmail.com"
            tvUserDesc.text = currentUsers.following_url
            tvLocation.text = currentUsers.node_id+",${currentUsers.login}"
            Glide.with(profileImage)
                .load(currentUsers.avatar_url)
                .into(profileImage)


        }
        holder.itemView.setOnClickListener {v->
            val intent = Intent( v.context, DetailUserActivity::class.java)
            intent.putExtra("USER_NAME",currentUsers.login)
            v.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }
}