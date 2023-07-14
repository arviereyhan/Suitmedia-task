package com.example.suitmediaproject.ui

import android.provider.ContactsContract.Data
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.suitmediaproject.data.remote.response.DataItem
import com.example.suitmediaproject.databinding.ItemRowUserBinding

class UserAdapter(private val listUser: List<DataItem>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    inner class ViewHolder(private val binding: ItemRowUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: DataItem) {
            binding.root.setOnClickListener {
                onItemClickListener?.onItemClick(user)
            }
            binding.tvUsername.text = user.firstName + " " + user.lastName
            binding.tvEmail.text = user.email
            Glide.with(itemView).load(user.avatar).into(binding.imgProfile)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemRowUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = listUser[position]
        holder.bind(user)
    }

    override fun getItemCount() = listUser.size

    interface OnItemClickListener {
        fun onItemClick(data: DataItem)
    }
}