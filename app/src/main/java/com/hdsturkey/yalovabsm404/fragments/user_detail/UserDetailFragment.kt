package com.hdsturkey.yalovabsm404.fragments.user_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.hdsturkey.yalovabsm404.databinding.FragmentUserDetailBinding


class UserDetailFragment : Fragment() {
    private lateinit var mBinding: FragmentUserDetailBinding

    private val args: UserDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentUserDetailBinding.inflate(layoutInflater)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userID = args.userID
        mBinding.tvUserDetail.text = "${mBinding.tvUserDetail.text.toString()} \n\n\n income user id : $userID"

    }

}