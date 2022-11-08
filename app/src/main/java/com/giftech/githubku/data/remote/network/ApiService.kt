package com.giftech.githubku.data.remote.network

import com.giftech.githubku.data.remote.dto.SearchUserDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/search/users")
    suspend fun getSearchedUser(
        @Query("q") keyword:String
    ):SearchUserDto
}