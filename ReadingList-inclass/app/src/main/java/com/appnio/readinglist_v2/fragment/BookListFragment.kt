package com.appnio.readinglist_v2.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.appnio.readinglist_v2.R
import com.appnio.readinglist_v2.ReadingApplication
import com.appnio.readinglist_v2.adapter.BookAdapter
import com.appnio.readinglist_v2.db.Book
import com.appnio.readinglist_v2.extension.replaceFragment
import kotlinx.android.synthetic.main.fragment_book_list.*


class BookListFragment : Fragment() {

    companion object {
        fun newInstance() = BookListFragment()
    }

    val adapter = BookAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_book_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bookRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
            adapter = this@BookListFragment.adapter
        }
        addBookButton.setOnClickListener {
            replaceFragment()
        }
        setBookData()
        activity?.let {
            (it.application as ReadingApplication)
                .repository
                .addListener {
                    setBookData()
                }
        }
        adapter.setOnItemClickListener {
            replaceFragment(it)
        }
    }

    private fun replaceFragment(book: Book? = null) {
        replaceFragment(EditBookFragment.newInstance(book), true)
    }

    fun setBookData() {
        activity?.let {
            (it.application as ReadingApplication)
                .repository
                .findAll { books ->
                    adapter.setBooks(books)
                }
        }
    }
}