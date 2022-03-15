package com.hdsturkey.yalovabsm404.fragments.user_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.hdsturkey.yalovabsm404.databinding.FragmentUserListBinding


class UserListFragment : Fragment() {
    private lateinit var mBinding : FragmentUserListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = FragmentUserListBinding.inflate(layoutInflater)

    }
}