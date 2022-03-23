package com.hdsturkey.yalovabsm404.fragments.user_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.hdsturkey.yalovabsm404.data.model.User
import com.hdsturkey.yalovabsm404.databinding.ItemUserBinding
import com.hdsturkey.yalovabsm404.utils.invisible
import com.hdsturkey.yalovabsm404.utils.show

class UserListAdapter constructor(
    private val clickListener: (position: Int) -> Unit,
    private val deleteClickListener: (position: Int) -> Unit
) : ListAdapter<User, UserListAdapter.UserViewHolder>(UserDiffCallBack()) {

    class UserViewHolder(private val mBinding: ItemUserBinding) : RecyclerView.ViewHolder(mBinding.root) {
        fun bind(user: User,onDeleteClick: () -> Unit) {
            mBinding.tvUserName.text = "${user.name.first}  ${user.name.last}"
            mBinding.tvGender.text = user.gender
            mBinding.tvPhone.text = user.phone
            mBinding.imgUserProfileImage.load(user.picture.medium)
            mBinding.btnDelete.setOnClickListener { onDeleteClick.invoke() }
        }

        companion object {
            fun from(parent: ViewGroup): UserViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemUserBinding.inflate(layoutInflater,parent,false) // //R.layout.item_user
                return UserViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder.from(parent)
    }

    override fun onBindViewHolder(userViewHolder: UserViewHolder, position: Int) {
        val item = getItem(position)
        userViewHolder.itemView.setOnClickListener { clickListener.invoke(userViewHolder.adapterPosition) }
        userViewHolder.bind(item) {
            deleteClickListener.invoke(userViewHolder.adapterPosition)
        }
    }

    private class UserDiffCallBack : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User) = oldItem.userId == newItem.userId

        override fun areContentsTheSame(oldItem: User, newItem: User) = oldItem == newItem
    }
}