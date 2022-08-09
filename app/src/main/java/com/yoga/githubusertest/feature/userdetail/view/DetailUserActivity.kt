package com.yoga.githubusertest.feature.userdetail.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.yoga.githubusertest.common.constant.Config
import com.yoga.githubusertest.common.constant.Config.Constant.replaceNull
import com.yoga.githubusertest.databinding.ActivityDetailUsersBinding
import com.yoga.githubusertest.feature.userdetail.view.adapter.ReposAdapter
import com.yoga.githubusertest.feature.userdetail.viewmodel.DetailUserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailUserActivity : AppCompatActivity() {
    private lateinit var binding :ActivityDetailUsersBinding
    private lateinit var userName :String
    private val viewModel : DetailUserViewModel by viewModels()
    private lateinit var reposAdapter: ReposAdapter


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userName = intent.getStringExtra("USER_NAME").toString()

        viewModel.getUserByName(userName)
        viewModel.getRepoByName(userName)
        initData()


    }

    private fun initData() {
        viewModel.detailResponse.observe(this) { listUsers ->
            binding.apply {
                Glide.with(imgUserDetail)
                    .load(listUsers.avatar_url)
                    .into(imgUserDetail)

                tvNameDetail.text = listUsers.login
                tvSocMedDetail.text = "@${listUsers.twitter_username.replaceNull()}"
                tvDescDetail.text = listUsers.company.replaceNull()
                tvFollowerCount.text = Config.Constant.countingConvert(listUsers.followers.toLong()).toString()
                tvFollowingCount.text =
                    Config.Constant.countingConvert(listUsers.following.toLong()).toString()
                tvLocationDetail.text = listUsers.location.replaceNull()
                tvEmailDetail.text = listUsers.email.replaceNull()


            }
        }
        initRecycler()
    }

    private fun initRecycler(){
        reposAdapter = ReposAdapter()
        binding.recyclerRepos.apply{
            adapter = reposAdapter
            layoutManager= LinearLayoutManager(this@DetailUserActivity,)

        }
        viewModel.reposResponse.observe(this) { listRepo ->
            reposAdapter.repos = listRepo
        }
    }
}