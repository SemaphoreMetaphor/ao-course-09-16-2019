package com.appnio.readinglist_v2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.appnio.readinglist_v2.R
import com.appnio.readinglist_v2.db.Book


class BookAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<Book> = arrayListOf()
    private var clickListener: ((Book) -> Unit)? = null

    fun setBooks(books: List<Book>) {
        items = books
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: (Book) -> Unit) {
        clickListener = listener
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BookItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_book,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is BookItemViewHolder -> holder.bind(items[position], clickListener)
        }
    }

    class BookItemViewHolder(val parent: View) : RecyclerView.ViewHolder(parent) {

        private val title: TextView = parent.findViewById(R.id.item_book_title)
        private val author: TextView = parent.findViewById(R.id.item_book_author)
        private val year: TextView = parent.findViewById(R.id.item_book_year)

        fun bind(book: Book, clickListener: ((Book) -> Unit)?) {
            title.text = book.title
            author.text = book.author
            year.text = book.year
            parent.setOnClickListener { clickListener?.invoke(book) }
        }
    }
}