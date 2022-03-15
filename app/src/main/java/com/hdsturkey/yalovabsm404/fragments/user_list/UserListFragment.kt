package com.hdsturkey.yalovabsm404.fragments.user_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.hdsturkey.yalovabsm404.R
import com.hdsturkey.yalovabsm404.databinding.FragmentUserListBinding


class UserListFragment : Fragment() {
    private lateinit var mBinding: FragmentUserListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()

    }

    private fun setListeners() {
        mBinding.tvUserList.setOnClickListener {
            if (isInputCorrect()) {

                mBinding.tilUserId.error = null

            } else {
                mBinding.tilUserId.error = "Please enter 6 digit user id"
            }
        }
    }



    private fun isInputCorrect(): Boolean {
        val userID = mBinding.etUserId.text

        if (userID.isNullOrBlank()) {
            return false
        }

        if (userID.toString().length<6) {
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
}