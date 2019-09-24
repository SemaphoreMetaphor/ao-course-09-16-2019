package com.appnio.todo.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.appnio.todo.R
import com.appnio.todo.adapter.TodoAdapter
import com.appnio.todo.db.RepositoryListener
import com.appnio.todo.db.Todo
import com.appnio.todo.extension.addFragment
import com.appnio.todo.extension.getRepository
import kotlinx.android.synthetic.main.fragment_todo_list.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


class TodoListFragment : Fragment(), RepositoryListener, CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    companion object {
        fun newInstance() = TodoListFragment()
    }

    private val adapter = TodoAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_todo_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getRepository().addListener(this)
        noteRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@TodoListFragment.adapter
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }
        adapter.setOnItemClickListener(object : TodoAdapter.ClickListener {
            override fun onItemClick(todo: Todo) {
                AlertDialog.Builder(context)
                    .setTitle(R.string.dialog_todo_title)
                    .setNegativeButton(R.string.dialog_todo_cancel) { _, _ ->  }
                    .setPositiveButton(R.string.dialog_todo_ok) { _, _ ->
                        getRepository().removeItem(todo)
                    }
                    .show()
            }

        })
        setItems()
        addTodoButton.setOnClickListener {
            addFragment(AddTodoFragment.newInstance(), true)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancelChildren()
    }

    override fun onUpdate() {
        setItems()
    }

    fun setItems() {
        launch {
            val items = getRepository().getAllItems()
            adapter.setItems(items)
        }

    }
}