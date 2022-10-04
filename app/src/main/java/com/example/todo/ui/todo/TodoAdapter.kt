package com.example.todo.ui.todo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.database.Todo
import com.example.todo.databinding.ItemTaskBinding

class TodoAdapter(
    private val onItemCLickedListener: OnItemCLickedListener,
    private val onCardCLickedListener: OnItemCLickedListener,
    private val onDeleteCLickedListener: OnItemCLickedListener
) : ListAdapter<Todo, TodoViewHolder>(TodoDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val task = getItem(position)
        holder.bind(task)

        holder.binding.markDone.setOnClickListener {
            onItemCLickedListener.onItemClicked(task)
        }
        holder.binding.content.setOnClickListener {
            onCardCLickedListener.onItemClicked(task)
        }
        holder.binding.delete.setOnClickListener {
            onDeleteCLickedListener.onItemClicked(task)
        }
    }
}

class TodoDiffCallback : DiffUtil.ItemCallback<Todo>() {
    override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem == newItem
    }

}

class TodoViewHolder(val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(todo: Todo) {
        binding.task = todo
    }

    companion object {
        fun from(parent: ViewGroup): TodoViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val view = ItemTaskBinding.inflate(inflater, parent, false)
            return TodoViewHolder(view)
        }
    }
}

class OnItemCLickedListener(val onItem: (task: Todo) -> Unit) {
    fun onItemClicked(task: Todo) = onItem(task)
}