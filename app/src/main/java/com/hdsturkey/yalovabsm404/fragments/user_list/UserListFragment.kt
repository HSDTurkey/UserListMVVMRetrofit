package com.hdsturkey.yalovabsm404.fragments.user_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hdsturkey.yalovabsm404.data.local.AppDatabase
import com.hdsturkey.yalovabsm404.data.model.User
import com.hdsturkey.yalovabsm404.data.model.UserName
import com.hdsturkey.yalovabsm404.data.model.UserPicture
import com.hdsturkey.yalovabsm404.databinding.FragmentUserListBinding
import com.hdsturkey.yalovabsm404.utils.toast


class UserListFragment : Fragment() {
    private lateinit var mBinding: FragmentUserListBinding

    private var userList: List<User> = listOf()
    private val userListAdapter = UserListAdapter(::userClicked, ::deleteUserClicked)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        insertMockUserListToDatabase()
        setListeners()
        setRecyclerView()
    }

    private fun insertMockUserListToDatabase() {
        val localDBUsers = AppDatabase.getInstance().userDao().getAllOneShot()
        if (localDBUsers.isEmpty()) {
            Log.d(TAG, "Starting to inserting mock data to DB ")
            val mockUserList = getMockUserList()
            AppDatabase.getInstance().userDao().insert(mockUserList)
            Log.d(TAG, "Mock data inserted to DB")
        } else {
            Log.d(TAG, "Mock data don't inserted to DB because already exists ")
        }
    }

    private fun observeUserListFromDB() {
        Log.d(TAG, "STARTING TO OBSERVE DATABASE CHANGINATIONS")
        AppDatabase.getInstance().userDao().getAll().observe(viewLifecycleOwner) { list ->
            Log.d(TAG, "New User list (size:${list.size}) has been delivered. Submitting to UI ")
            updateUserList(list)
        }
    }

    private fun updateUserList(list: List<User>) {
        userList = list
        userListAdapter.submitList(list.toMutableList())
    }

    private fun setRecyclerView() {
        val mLayoutManager = LinearLayoutManager(context)
        mLayoutManager.orientation = RecyclerView.VERTICAL      //Dikeyde listeleme yapar
//        mLayoutManager.orientation = RecyclerView.HORIZONTAL  //Yatayda listeleme yapar
        mBinding.rvUserList.apply {
            adapter = userListAdapter
            layoutManager = mLayoutManager
            setHasFixedSize(true)
        }
        Log.d(TAG, "RECYCLERVIEW INITIALIZED")

        val itemDecoration = DividerItemDecoration(
            mBinding.rvUserList.context,
            mLayoutManager.orientation
        )
        mBinding.rvUserList.addItemDecoration(itemDecoration)
    }

    private fun userClicked(position: Int) {
        val clickedUser = userList[position]
        toast("${clickedUser.name.first}")
        navigateToDetailScreen("${clickedUser.name.first} ${clickedUser.name.last}")
    }

    private fun deleteUserClicked(position: Int) {
        Log.d(TAG, "USER DELETE CLICKED for position $position")
        val user = userList[position]
        AppDatabase.getInstance().userDao().delete(user)
        Log.d(TAG, "USER DELETED ${user.name.first}")
    }

    private fun setListeners() {
        mBinding.tvUserList.setOnClickListener {
            observeUserListFromDB()
//
//            if (isInputCorrect()) {
//
//                mBinding.tilUserId.error = null
//
//                val userID = mBinding.etUserId.text.toString()
//                navigateToDetailScreen(userID)
//            } else {
//                mBinding.tilUserId.error = "Please enter 6 digit user id"
//            }
        }

        mBinding.fabInsertUser.setOnClickListener {
            val user = getMockUserList()[6]
            AppDatabase.getInstance().userDao().insert(user)
            Log.d(TAG, "NEW USER INSERTED")
        }
    }

    private fun navigateToDetailScreen(userID: String) {
        val action = UserListFragmentDirections.actionUserListFragmentToUserDetailFragment(userID)
        findNavController().navigate(action)
    }

    private fun isInputCorrect(): Boolean {
        val userID = mBinding.etUserId.text

        if (userID.isNullOrBlank()) {
            return false
        }

        if (userID.toString().length < 6) {
            return false
        }

        return true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentUserListBinding.inflate(layoutInflater)
        return mBinding.root
    }

    private fun getMockUserList(): List<User> {
        return listOf(
            User(
                UserName("CT", "Cengiz", "TORU"),
                "Male",
                "12323443",
                UserPicture(
                    "https://avatars.githubusercontent.com/u/28221219?v=4",
                    "https://avatars.githubusercontent.com/u/28221219?v=4",
                    "https://avatars.githubusercontent.com/u/28221219?v=4"
                )
            ),
            User(
                UserName("Mademoiselle", "Eliana", "Bernard"),
                "Female",
                "078 359 26 63",
                UserPicture(
                    "https://randomuser.me/api/portraits/women/33.jpg",
                    "https://randomuser.me/api/portraits/med/women/33.jpg",
                    "https://randomuser.me/api/portraits/thumb/women/33.jpg"
                )
            ),
            User(
                UserName("Mr", "Boaventura", "Teixeira"),
                "male",
                "(97) 4382-7544",
                UserPicture(
                    "https://randomuser.me/api/portraits/men/41.jpg",
                    "https://randomuser.me/api/portraits/med/men/41.jpg",
                    "https://randomuser.me/api/portraits/thumb/men/41.jpg"
                )
            ),
            User(
                UserName("Miss", "Franka", "Wagenknecht"),
                "female",
                "0426-0171993",
                UserPicture(
                    "https://randomuser.me/api/portraits/women/10.jpg",
                    "https://randomuser.me/api/portraits/med/women/10.jpg",
                    "https://randomuser.me/api/portraits/thumb/women/10.jpg"
                )
            ),
            User(
                UserName("Mademoiselle", "Eliana", "Bernard"),
                "Female",
                "078 359 26 63",
                UserPicture(
                    "https://randomuser.me/api/portraits/women/33.jpg",
                    "https://randomuser.me/api/portraits/med/women/33.jpg",
                    "https://randomuser.me/api/portraits/thumb/women/33.jpg"
                )
            ),
            User(
                UserName("Mr", "Boaventura", "Teixeira"),
                "male",
                "(97) 4382-7544",
                UserPicture(
                    "https://randomuser.me/api/portraits/men/41.jpg",
                    "https://randomuser.me/api/portraits/med/men/41.jpg",
                    "https://randomuser.me/api/portraits/thumb/men/41.jpg"
                )
            ),
            User(
                UserName("CT", "Cengiz", "TORU"),
                "Male",
                "12323443",
                UserPicture(
                    "https://avatars.githubusercontent.com/u/28221219?v=4",
                    "https://avatars.githubusercontent.com/u/28221219?v=4",
                    "https://avatars.githubusercontent.com/u/28221219?v=4"
                )
            ),
            User(
                UserName("Mr", "Boaventura", "Teixeira"),
                "male",
                "(97) 4382-7544",
                UserPicture(
                    "https://randomuser.me/api/portraits/men/41.jpg",
                    "https://randomuser.me/api/portraits/med/men/41.jpg",
                    "https://randomuser.me/api/portraits/thumb/men/41.jpg"
                )
            ),
        )
    }

    companion object {
        const val TAG = "USERLISTFRAGMENT"
    }
}