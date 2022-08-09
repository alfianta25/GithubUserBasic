package com.yoga.githubusertest.feature.userdetail.model

import com.yoga.githubusertest.common.service.AppServicesInterface
import javax.inject.Inject

class DetailUsersRepo
@Inject constructor(private val retroInstance: AppServicesInterface) {
    suspend  fun getUsersByName(username:String) = retroInstance.getUsersByName(username)
    suspend  fun getReposByName(username:String) = retroInstance.getReposByName(username)

}