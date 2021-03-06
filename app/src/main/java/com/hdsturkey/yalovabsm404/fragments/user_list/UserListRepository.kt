package com.hdsturkey.yalovabsm404.fragments.user_list

import android.util.Log
import androidx.lifecycle.liveData
import com.hdsturkey.yalovabsm404.data.local.AppDatabase
import com.hdsturkey.yalovabsm404.data.model.User
import com.hdsturkey.yalovabsm404.utils.NetworkHelper

class UserListRepository {
    suspend fun getUsers(userCount: Int) = liveData {
        val storedUsers = AppDatabase.getInstance().userDao().getAll()
        emitSource(storedUsers)


        val remoteUsers = NetworkHelper.getServices().getUserList(userCount).results
        if (remoteUsers.isNullOrEmpty().not()) {
            AppDatabase.getInstance().userDao().insert(remoteUsers)
        } else {
            Log.e("UserListRepository", "Fetching user list from remote failed. Local user list will shown.")
        }
    }

    suspend fun deleteUser(user: User) {
        AppDatabase.getInstance().userDao().delete(user)
    }

    suspend fun deleteAllUsers() {
        AppDatabase.getInstance().userDao().deleteAll()
    }


    suspend fun saveUser(user: User) {
        AppDatabase.getInstance().userDao().insert(user)
    }
}
