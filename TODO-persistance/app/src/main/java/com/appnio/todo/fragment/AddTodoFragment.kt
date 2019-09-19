package com.appnio.todo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.appnio.todo.R
import com.appnio.todo.db.Todo
import com.appnio.todo.extension.getRepository
import com.appnio.todo.extension.popBackStack
import kotlinx.android.synthetic.main.fragment_add_todo.*


class AddTodoFragment : Fragment() {

    companion object {
        fun newInstance() = AddTodoFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_add_todo, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addTodo.setOnClickListener {
            getRepository().addItem(Todo(null, todoText.text.toString()))
            popBackStack()
        }
    }
}