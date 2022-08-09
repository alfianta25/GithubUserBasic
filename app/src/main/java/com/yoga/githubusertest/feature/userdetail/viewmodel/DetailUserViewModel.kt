package com.yoga.githubusertest.feature.userdetail.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yoga.githubusertest.feature.userdetail.model.DetailUsersRepo
import com.yoga.githubusertest.feature.userdetail.model.RepoModel
import com.yoga.githubusertest.feature.userdetail.model.UserDetailModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailUserViewModel @Inject constructor(private val detailRepo: DetailUsersRepo): ViewModel(){
    private val _detailResponse= MutableLiveData<UserDetailModel>()
    private val _reposResponse = MutableLiveData<RepoModel>()
    val detailResponse : LiveData<UserDetailModel>
        get() = _detailResponse
    val reposResponse : LiveData<RepoModel>
        get()=_reposResponse

    lateinit var  name :String



    fun getUserByName(username:String)=viewModelScope.launch{

        detailRepo.getUsersByName(username).let {
                response ->
            if(response.isSuccessful){
                _detailResponse.postValue(response.body())

            }else{
                Log.d("failed messages","something error on detailuser: ${response.code()}")
            }
        }

    }

    fun getRepoByName(username: String)=viewModelScope.launch {
        detailRepo.getReposByName(username).let{
                response ->
            if(response.isSuccessful){
                _reposResponse.postValue(response.body())
            }else{
                Log.d("failed messages","something error on repos: ${response.code()}")
            }
        }

    }
}