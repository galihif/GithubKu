package com.giftech.githubku.ui.detail

import androidx.lifecycle.*
import com.giftech.githubku.data.MainRepository
import com.giftech.githubku.data.model.Repo
import com.giftech.githubku.data.model.User
import com.giftech.githubku.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel
@Inject constructor(
    repository:MainRepository
) : ViewModel() {

    val test = "shit"
    private val _username = MutableLiveData<String>()

    private val _user = _username.switchMap { username->
        repository.getDetailUser(username).asLiveData()
    }
    val user:LiveData<Resource<User>> = _user

    private val _listRepo = _username.switchMap { username ->
        repository.getListRepo(username).asLiveData()
    }
    val listRepo:LiveData<Resource<List<Repo>>> = _listRepo

    fun setUsername(username:String){
        if (username == _username.value || username.isEmpty()){
            return
        }
        _username.value = username
    }
}