package com.appnio.todo_v2.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.appnio.todo_v2.R
import com.appnio.todo_v2.adapter.TodoAdapter
import com.appnio.todo_v2.db.RepositoryListener
import com.appnio.todo_v2.extension.addFragment
import com.appnio.todo_v2.extension.getRepository
import com.appnio.todo_v2.model.Todo
import kotlinx.android.synthetic.main.fragment_todo_list.*


class TodoListFragment : Fragment(), TodoAdapter.TodoClickListener {

    companion object {
        fun newInstance() = TodoListFragment()
    }

    val adapter = TodoAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_todo_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        todoRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
            adapter = this@TodoListFragment.adapter
        }
        addTodoButton.setOnClickListener {
            addFragment(AddTodoFragment.newInstance(), true)
        }
        adapter.setOnClickListener(this)
        adapter.setTodoItems(getRepository().findAll())
        getRepository().addListener(object : RepositoryListener {
            override fun onUpdate() {
                adapter.setTodoItems(getRepository().findAll())
            }
        })
    }

    override fun onItemClick(todo: Todo) {
        AlertDialog.Builder(context!!)
            .setTitle(R.string.dialog_todo_title)
            .setNegativeButton(R.string.dialog_todo_negative, { _, _ -> })
            .setPositiveButton(R.string.dialog_todo_positive) { _, _ -> getRepository().delete(todo) }
            .show()
    }
}