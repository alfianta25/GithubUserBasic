package com.yoga.githubusertest.feature.usermain.model

import com.yoga.githubusertest.common.service.AppServicesInterface
import javax.inject.Inject

class UsersRepo
@Inject constructor(private val retroServiceInstance: AppServicesInterface) {
    suspend fun getUsers() = retroServiceInstance.getUsers()
}