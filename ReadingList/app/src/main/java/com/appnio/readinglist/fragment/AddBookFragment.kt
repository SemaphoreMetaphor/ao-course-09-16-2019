package com.appnio.readinglist.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.appnio.readinglist.R
import com.appnio.readinglist.ReadingApplication
import com.appnio.readinglist.activity.MainActivity
import com.appnio.readinglist.db.Book
import com.appnio.readinglist.extension.textString
import kotlinx.android.synthetic.main.fragment_add_book.*


class AddBookFragment : Fragment() {

    companion object {
        const val BOOK_KEY = "BOOK_KEY"
        fun newInstance(book: Book? = null): Fragment {
            val fragment = AddBookFragment()
            val bundle = Bundle()
            bundle.putSerializable(BOOK_KEY, book)
            fragment.arguments = bundle
            return fragment
        }
    }

    var argumentBook: Book? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_add_book, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getSerializable(BOOK_KEY)?.let {
            argumentBook = it as Book
            addBookTitle.setText(argumentBook!!.title)
            addBookAuthor.setText(argumentBook!!.author)
            addBookYear.setText(argumentBook!!.year)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_add_book, menu)
        if (argumentBook == null) {
            menu.findItem(R.id.action_delete).isVisible = false
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save -> {
                saveBook()
                true
            }
            R.id.action_delete -> {
                AlertDialog.Builder(context)
                    .setTitle(R.string.delete_book_title)
                    .setNegativeButton(android.R.string.cancel) { _, _ -> }
                    .setPositiveButton(android.R.string.ok) { _, _ -> deleteBook() }
                    .show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    private fun deleteBook() {
        argumentBook?.let {
            ((activity as MainActivity)
                .application as ReadingApplication)
                .repository
                .delete(argumentBook!!)
            popBackStack()
        }
    }

    private fun saveBook() {
        if (!isDataValid()) {
            return
        }
        ((activity as MainActivity)
            .application as ReadingApplication)
            .repository
            .insert(
                Book(
                    if (argumentBook == null) null else argumentBook!!.id,
                    addBookAuthor.textString(),
                    addBookTitle.textString(),
                    addBookYear.textString()
                )
            )
        popBackStack()
    }

    private fun popBackStack() {
        activity?.let {
            (activity as MainActivity).supportFragmentManager.popBackStack()
        }
    }

    private fun isDataValid(): Boolean {
        val title = addBookTitle.textString()
        val author = addBookAuthor.textString()
        val year = addBookYear.textString()
        if (title.isBlank()) {
            addBookTitle.error = "Title can not be blank"
            return false
        }
        if (author.isBlank()) {
            addBookAuthor.error = "Author can not be blank"
            return false
        }
        if (year.isBlank()) {
            addBookYear.error = "Year can not be blank"
            return false
        }
        return true
    }
}