package com.appnio.todo_v2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.appnio.todo_v2.R
import com.appnio.todo_v2.model.Todo


class TodoAdapter : RecyclerView.Adapter<TodoAdapter.TodoItemViewHolder>() {

    var items: List<Todo> = arrayListOf()
    var listener: TodoClickListener? = null

    fun setTodoItems(newItems: List<Todo>) {
        items = newItems
        notifyDataSetChanged()
    }

    fun setOnClickListener(listener: TodoClickListener) {
        this.listener = listener
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

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: TodoItemViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    class TodoItemViewHolder(val parent: View) : RecyclerView.ViewHolder(parent) {

        var todoText: TextView = parent.findViewById(R.id.todo_item_text)

        fun bind(todo: Todo, listener: TodoClickListener?) {
            todoText.text = todo.text
            parent.setOnClickListener { listener?.onItemClick(todo) }
        }
    }

    interface TodoClickListener {
        fun onItemClick(todo: Todo)
    }
}