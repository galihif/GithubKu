package com.giftech.githubku.ui.home

import androidx.lifecycle.*
import com.giftech.githubku.data.MainRepository
import com.giftech.githubku.data.model.User
import com.giftech.githubku.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(
    repository:MainRepository
) : ViewModel() {

    private val _query = MutableLiveData<String>()

    private val _users = _query.switchMap { query ->
        repository.searchUserByQuery(query).asLiveData()
    }
    val users: LiveData<Resource<List<User>>> = _users

    fun setQuery(query:String){
        if (query == _query.value || query.isEmpty()){
            return
        }
        _query.value = query
    }

    init {
        _query.value = "Michael"
    }
}