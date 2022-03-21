package com.hdsturkey.yalovabsm404.fragments.user_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hdsturkey.yalovabsm404.databinding.FragmentUserListBinding
import com.hdsturkey.yalovabsm404.fragments.user_list.model.User
import com.hdsturkey.yalovabsm404.fragments.user_list.model.UserName
import com.hdsturkey.yalovabsm404.fragments.user_list.model.UserPicture


class UserListFragment : Fragment() {
    private lateinit var mBinding: FragmentUserListBinding

    private val userList: List<User> by lazy { getUsers() }
    private val userListAdapter = UserListAdapter(::userClicked)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
        setRecyclerView()

    }

    private fun setRecyclerView() {
        mBinding.rvUserList.apply {
            adapter = userListAdapter
            layoutManager = LinearLayoutManager(context)
        }
        userListAdapter.submitList(userList)
    }

    private fun userClicked(position: Int) {

    }

    private fun setListeners() {
        mBinding.tvUserList.setOnClickListener {
            if (isInputCorrect()) {

                mBinding.tilUserId.error = null

                val userID = mBinding.etUserId.text.toString()
                navigateToDetailScreen(userID)
            } else {
                mBinding.tilUserId.error = "Please enter 6 digit user id"
            }
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

    private fun getUsers(): List<User> {
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
}