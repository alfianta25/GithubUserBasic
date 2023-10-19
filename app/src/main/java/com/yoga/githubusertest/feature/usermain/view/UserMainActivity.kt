package com.yoga.githubusertest.feature.usermain.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.yoga.githubusertest.databinding.ActivityMainUsersBinding
import com.yoga.githubusertest.feature.usermain.view.adapter.SearchAdapter
import com.yoga.githubusertest.feature.usermain.view.adapter.UsersListAdapter
import com.yoga.githubusertest.feature.usermain.viewmodel.UserMainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main_users.*

@AndroidEntryPoint
class UserMainActivity : AppCompatActivity(){

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, UserMainActivity::class.java)
            context.startActivity(intent)
        }
    }

    private lateinit var binding: ActivityMainUsersBinding
    private val viewModel : UserMainViewModel by viewModels()
    private lateinit var userslistAdapter : UsersListAdapter
    private lateinit var usersSearchAdapter : SearchAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecycler()
        addTextListener()


    }

    private fun addTextListener(){
        etSearchBar.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
//                initRecyclerSearch()
                viewModel.doSearchUsers(etSearchBar.text.toString())
                return@OnKeyListener true
            }
            false
        })

        etSearchBar.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if(s.toString() == ""){
                    initRecycler()
                }
            }

        })


    }

//    private fun initRecyclerSearch(){
//        usersSearchAdapter = UsersSearchAdapter()
//        binding.recyclerUser.apply{
//            adapter = usersSearchAdapter
//            layoutManager= LinearLayoutManager(this@UserMainActivity,)
//
//        }
//        viewModel.responseSearch.observe(this) { listUsers ->
//            usersSearchAdapter.users = listUsers.items
//        }
//    }


    private fun initRecycler(){
        userslistAdapter = UsersListAdapter()
        binding.listUsers.apply{
            adapter = userslistAdapter
            layoutManager= LinearLayoutManager(this@UserMainActivity,)

        }
        viewModel.responseUsers.observe(this) { listUsers ->
            userslistAdapter.users = listUsers
        }
    }

}