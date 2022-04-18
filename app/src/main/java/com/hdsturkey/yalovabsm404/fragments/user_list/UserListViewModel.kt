package com.hdsturkey.yalovabsm404.fragments.user_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hdsturkey.yalovabsm404.data.model.User
import kotlinx.coroutines.launch


class UserListViewModel : ViewModel() {

    private val userListRepository = UserListRepository()

    fun getUsers(userCount: Int): LiveData<List<User>> {
        var userListLiveData: LiveData<List<User>> = MutableLiveData(emptyList())

        viewModelScope.launch {
            userListLiveData = userListRepository.getUsers(userCount)
        }

        return userListLiveData
    }

}