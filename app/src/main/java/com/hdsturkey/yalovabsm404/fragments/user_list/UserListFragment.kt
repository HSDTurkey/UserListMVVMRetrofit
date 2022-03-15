package com.hdsturkey.yalovabsm404.fragments.user_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.hdsturkey.yalovabsm404.R
import com.hdsturkey.yalovabsm404.databinding.FragmentUserListBinding


class UserListFragment : Fragment() {
    private lateinit var mBinding: FragmentUserListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = FragmentUserListBinding.inflate(layoutInflater)

        setListeners()

    }

    private fun setListeners() {
        mBinding.tvUserList.setOnClickListener {
            findNavController().navigate(R.id.action_userListFragment_to_userDetailFragment)
        }
    }
}