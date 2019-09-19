package com.appnio.readinglist_v2.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.appnio.readinglist_v2.R
import com.appnio.readinglist_v2.ReadingApplication
import com.appnio.readinglist_v2.db.Book
import kotlinx.android.synthetic.main.fragment_edit_book.*


class EditBookFragment : Fragment() {

    companion object {
        fun newInstance() = EditBookFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_edit_book, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_edit_book, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save -> {
                activity?.let {
                    saveBook()
                    it.supportFragmentManager.popBackStack()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun saveBook() {
        activity?.let {
            (it.application as ReadingApplication)
                .repository
                .insert(
                    Book(
                        null,
                        editTitle.text.toString(),
                        editAuthor.text.toString(),
                        editYear.text.toString()
                    )
                )
        }
    }
}