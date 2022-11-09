package com.giftech.githubku.data.remote.network

import com.giftech.githubku.data.remote.dto.DetailUserDto
import com.giftech.githubku.data.remote.dto.RepoDto
import com.giftech.githubku.data.remote.dto.SearchUserDto
import com.giftech.githubku.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/search/users")
    suspend fun getSearchedUser(
        @Query("q") keyword:String
    ):SearchUserDto

    @GET("/users/{username}")
    suspend fun getDetailUser(
        @Path("username") username:String,
        @Header("Authorization") token: String = Constants.ACCESS_TOKEN
    ):DetailUserDto

    @GET("/users/{username}/repos")
    suspend fun getListRepo(
        @Path("username") username:String,
    ):List<RepoDto>


}