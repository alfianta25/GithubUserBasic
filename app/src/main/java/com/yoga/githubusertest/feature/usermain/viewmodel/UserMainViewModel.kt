package com.yoga.githubusertest.feature.usermain.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yoga.githubusertest.feature.usermain.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserMainViewModel @Inject constructor(private val userRepo: UsersRepo, private val searchUsersRepo : UsersSearchRepo): ViewModel(){

    private val _response = MutableLiveData<List<UserListResponseItem>>()
//    private val _searchResult = MutableLiveData<UsersSearchResponse>()
    val responseUsers: LiveData<List<UserListResponseItem>>
        get() =_response
//    val responseSearch : LiveData<UsersSearchResponse>
//        get() = _searchResult

    init{
        getAllUsers()
    }
    private fun getAllUsers() = viewModelScope.launch{
        userRepo.getUsers().let {
                response ->
            if(response.isSuccessful){
                _response.postValue(response.body())
            }else{
                Log.d("failed messages","something error: ${response.code()}")
            }
        }
    }

    fun doSearchUsers(keyword :String) =viewModelScope.launch {
        searchUsersRepo.searchUsers(keyword).let {
                response ->
            if(response.isSuccessful){
//                _searchResult.postValue(response.body())
            }else{
                Log.d("failed messages","something error: ${response.code()}")
            }
        }

    }



}