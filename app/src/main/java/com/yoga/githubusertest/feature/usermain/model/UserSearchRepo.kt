package com.yoga.githubusertest.feature.usermain.model

import com.yoga.githubusertest.common.service.AppServicesInterface
import javax.inject.Inject

class UsersSearchRepo
@Inject constructor(private val retroInstance: AppServicesInterface) {
    suspend fun searchUsers(keyword: String) = retroInstance.searchUsers(keyword)
}