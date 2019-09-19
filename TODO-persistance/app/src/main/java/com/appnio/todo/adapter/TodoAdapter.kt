package com.appnio.todo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.appnio.todo.R
import com.appnio.todo.db.Todo


class TodoAdapter : RecyclerView.Adapter<TodoAdapter.TodoItemViewHolder>() {

    private var itemList: List<Todo> = arrayListOf()
    private var clickListener: ClickListener? = null

    fun setItems(newItems: List<Todo>) {
        itemList = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoItemViewHolder {
        return TodoItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: TodoItemViewHolder, position: Int) {
        holder.bind(itemList[position], clickListener)
    }

    fun setOnItemClickListener(listener: ClickListener) {
        clickListener = listener
        notifyDataSetChanged()
    }

    class TodoItemViewHolder(private val parent: View) : RecyclerView.ViewHolder(parent) {

        private var todoItemText: TextView = parent.findViewById(R.id.todoItemText)

        fun bind(
            todo: Todo,
            clickListener: ClickListener?
        ) {
            parent.setOnClickListener { clickListener?.onItemClick(todo) }
            todoItemText.text = todo.text
        }
    }

    interface ClickListener {
        fun onItemClick(todo: Todo)
    }
}