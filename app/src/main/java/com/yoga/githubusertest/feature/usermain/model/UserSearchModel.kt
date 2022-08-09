package com.yoga.githubusertest.feature.usermain.model

data class UserSearchModel (
    val incomplete_results: Boolean,
    val items: List<UsersItem>,
    val total_count: Int
)