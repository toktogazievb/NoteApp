package com.example.noteapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.data.models.NoteModel
import com.example.noteapp.databinding.ItemChatBinding
import com.example.noteapp.ui.fragments.chat.ChatFragment

class ChatAdapter : ListAdapter<String, ChatAdapter.ViewHolder>(DiffCallback()) {
    class ViewHolder(private val binding: ItemChatBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(message: String?) {
            binding.itemMessage.text = message
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatAdapter.ViewHolder {
        val binding = ItemChatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatAdapter.ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }


    class DiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

    }
}