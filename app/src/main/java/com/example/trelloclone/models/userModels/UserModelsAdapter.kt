package com.example.trelloclone.models.userModels

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.trelloclone.databinding.MaildatarecylerviewitemBinding

class UserModelsAdapter(val userModels: UserModels) :
    RecyclerView.Adapter<UserModelsAdapter.UserModelViewHolder>() {


    inner class UserModelViewHolder(val itemBinding: MaildatarecylerviewitemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bindItem(task: UserX) {
            itemBinding.ivImage?.setImageURI(Uri.parse(task.image))

            Glide.with(itemView).load(task.image).into(itemBinding.ivImage)


            itemBinding.title?.text = task.firstName
            itemBinding.subtitle?.text = task.email
            itemBinding.time?.text = task.birthDate

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserModelViewHolder {
        return UserModelViewHolder(
            MaildatarecylerviewitemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun getItemCount(): Int {

        return userModels.users.size
    }

    override fun onBindViewHolder(holder: UserModelViewHolder, position: Int) {

        val usersData = userModels.users[position]
//        Log.d("Loading Data", usersData.toString())

        holder.bindItem(usersData)
    }
}