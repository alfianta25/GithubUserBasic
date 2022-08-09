package com.yoga.githubusertest.common.service

import com.yoga.githubusertest.feature.userdetail.model.RepoModel
import com.yoga.githubusertest.feature.userdetail.model.UserDetailModel
import com.yoga.githubusertest.feature.usermain.model.UserListModel
import com.yoga.githubusertest.feature.usermain.model.UserSearchModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AppServicesInterface {
    @GET("users")
    suspend fun getUsers(): Response<UserListModel>

    @GET("search/users")
    suspend fun searchUsers(@Query("q")query: String): Response<UserSearchModel>

    @GET("users/{username}")
    suspend fun getUsersByName(@Path("username")username:String): Response<UserDetailModel>

    @GET("users/{username}/repos")
    suspend fun  getReposByName(@Path("username")username: String): Response<RepoModel>
}