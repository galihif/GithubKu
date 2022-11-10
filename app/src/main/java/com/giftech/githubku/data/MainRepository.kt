package com.giftech.githubku.data

import com.giftech.githubku.data.model.Repo
import com.giftech.githubku.data.model.User
import com.giftech.githubku.data.remote.dto.toRepo
import com.giftech.githubku.data.remote.dto.toUser
import com.giftech.githubku.data.remote.network.ApiService
import com.giftech.githubku.utils.EspressoIdlingResource
import com.giftech.githubku.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val api:ApiService
) {
    fun searchUserByQuery(query:String):Flow<Resource<List<User>>> =
        flow {
            emit(Resource.Loading)
            try {
                val res = api.getSearchedUser(query).items.map { it.toUser() }
                emit(Resource.Success(res))
            } catch (e: HttpException) {
                emit(Resource.Error(e.message() ?: "Unexpected error occured"))
            } catch (e: IOException) {
                emit(Resource.Error(e.message ?: "Unexpected error occured"))
            }
        }.flowOn(Dispatchers.IO)

    fun getDetailUser(username:String):Flow<Resource<User>> =
        flow {
            emit(Resource.Loading)
            try {
                val res = api.getDetailUser(username).toUser()
                emit(Resource.Success(res))
            } catch (e: HttpException) {
                emit(Resource.Error(e.message() ?: "Unexpected error occured"))
            } catch (e: IOException) {
                emit(Resource.Error(e.message ?: "Unexpected error occured"))
            }
        }.flowOn(Dispatchers.IO)

    fun getListRepo(username:String):Flow<Resource<List<Repo>>> =
        flow {
            EspressoIdlingResource.increment()
            emit(Resource.Loading)
            try {
                val res = api.getListRepo(username).map { it.toRepo() }
                emit(Resource.Success(res))
                EspressoIdlingResource.decrement()
            } catch (e: HttpException) {
                EspressoIdlingResource.decrement()
                emit(Resource.Error(e.message() ?: "Unexpected error occured"))
            } catch (e: IOException) {
                EspressoIdlingResource.decrement()
                emit(Resource.Error(e.message ?: "Unexpected error occured"))
            }
        }.flowOn(Dispatchers.IO)



}