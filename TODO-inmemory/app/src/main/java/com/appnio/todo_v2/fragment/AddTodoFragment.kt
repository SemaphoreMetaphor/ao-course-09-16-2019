package com.appnio.todo_v2.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.appnio.todo_v2.R
import com.appnio.todo_v2.extension.getRepository
import com.appnio.todo_v2.extension.popBackstack
import com.appnio.todo_v2.model.Todo
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
        todoText.requestFocus()
        submitTodoButton.setOnClickListener {
            val todoString = todoText.text.toString()
            if (todoString.isNotBlank()) {
                getRepository().add(Todo(todoString))
                popBackstack()
            } else {
                todoText.error = "Todo can not be blank"
            }
        }
    }
}