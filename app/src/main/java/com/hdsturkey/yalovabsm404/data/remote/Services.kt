package com.hdsturkey.yalovabsm404.data.remote

import com.hdsturkey.yalovabsm404.data.remote.response.UserListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface Services {
    @GET("api")
    suspend fun getUserList(
        @Query("results") resultCount: Int
    ): UserListResponse
}