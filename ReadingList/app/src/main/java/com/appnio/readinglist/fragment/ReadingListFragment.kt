package com.appnio.readinglist.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.appnio.readinglist.R
import com.appnio.readinglist.ReadingApplication
import com.appnio.readinglist.activity.MainActivity
import com.appnio.readinglist.adapter.BookAdapter
import com.appnio.readinglist.db.Book
import com.appnio.readinglist.db.DataChangeListener
import kotlinx.android.synthetic.main.fragment_reading_list.*


class ReadingListFragment : Fragment() {

    companion object {
        fun newInstance() = ReadingListFragment()
    }

    val adapter = BookAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_reading_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        readingRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@ReadingListFragment.adapter
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }
        addBookButton.setOnClickListener {
            (activity as MainActivity).replaceFragment(AddBookFragment.newInstance(), true)
        }
        ((activity as MainActivity)
            .application as ReadingApplication)
            .repository
            .addDataChangeListener(object : DataChangeListener {
                override fun onDataUpdated() {
                    setBooksToAdapter()
                }

            })
        adapter.setOnItemClickListener(object : BookAdapter.ClickListener {
            override fun onItemClick(book: Book) {
                (activity as MainActivity).replaceFragment(AddBookFragment.newInstance(book), true)
            }

        })
        setBooksToAdapter()
    }

    private fun setBooksToAdapter() {
        activity?.let {
            ((activity as MainActivity)
                .application as ReadingApplication)
                .repository
                .getAll {
                    adapter.setBookItems(it)
                }
        }
    }
}