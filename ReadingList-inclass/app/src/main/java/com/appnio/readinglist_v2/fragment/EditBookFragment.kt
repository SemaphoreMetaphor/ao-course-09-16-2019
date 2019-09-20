package com.appnio.readinglist_v2.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.appnio.readinglist_v2.R
import com.appnio.readinglist_v2.ReadingApplication
import com.appnio.readinglist_v2.db.Book
import kotlinx.android.synthetic.main.fragment_edit_book.*


class EditBookFragment : Fragment() {

    companion object {
        const val BOOK_KEY = "BOOK_KEY"
        fun newInstance(book: Book? = null): Fragment {
            val fragment = EditBookFragment()
            val bundle = Bundle()
            bundle.putSerializable(BOOK_KEY, book)
            fragment.arguments = bundle
            return fragment
        }
    }

    var bookArgument: Book? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_edit_book, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bookArgument = arguments?.getSerializable(BOOK_KEY) as Book?
        bookArgument?.apply {
            editTitle.setText(title)
            editAuthor.setText(author)
            editYear.setText(year)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_edit_book, menu)
        menu.findItem(R.id.action_delete).isVisible = bookArgument != null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save -> {
                if (!isDataValid()) {
                    return false
                }
                activity?.let {
                    saveBook()
                    it.supportFragmentManager.popBackStack()
                }
                true
            }
            R.id.action_delete -> {
                activity?.let {
                    AlertDialog.Builder(context)
                        .setTitle(R.string.delete_book_dialog_title)
                        .setNegativeButton(android.R.string.cancel) { _, _ -> }
                        .setPositiveButton(android.R.string.ok) { _, _ ->
                            deleteBook()
                            it.supportFragmentManager.popBackStack()
                        }
                        .show()
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
                        bookArgument?.id,
                        editTitle.text.toString(),
                        editAuthor.text.toString(),
                        editYear.text.toString()
                    )
                )
        }
    }

    fun deleteBook() {
        activity?.let {
            (it.application as ReadingApplication)
                .repository
                .delete(bookArgument!!)
        }
    }

    fun isDataValid(): Boolean {
        var isValid = true
        if (editTitle.text.toString().isBlank()) {
            editTitle.error = "Title can not be blank"
            isValid = false
        }
        if (editAuthor.text.toString().isBlank()) {
            editAuthor.error = "Author can not be blank"
            isValid = false
        }
        if (editYear.text.toString().isBlank()) {
            editYear.error = "Year can not be blank"
            isValid = false
        }
        return isValid
    }
}