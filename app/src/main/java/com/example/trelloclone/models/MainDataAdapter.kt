package com.example.trelloclone.models

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.trelloclone.databinding.MaildatarecylerviewitemBinding
import com.squareup.picasso.Picasso

class MainDataAdapter(
    val taskList: List<MailData>,
) : RecyclerView.Adapter<MainDataAdapter.MainViewHolder>() {
    inner class MainViewHolder(val itemBinding: MaildatarecylerviewitemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bindItem(task: MailData) {
            Glide.with(itemView).load(task.image).centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(itemBinding.ivImage);
//            Picasso.get().load(task.image).into(itemBinding.ivImage);

            itemBinding?.ivImage?.setImageURI(Uri.parse(task.image))
            itemBinding?.title?.text = task.title
            itemBinding?.subtitle?.text = task.subtitle
            itemBinding?.time?.text = task.time
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            MaildatarecylerviewitemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val task = taskList[position]
        Log.d("Loading Data", taskList[position].toString())
        holder.bindItem(task)

    }

    override fun getItemCount(): Int {
        return taskList.size

    }
}



