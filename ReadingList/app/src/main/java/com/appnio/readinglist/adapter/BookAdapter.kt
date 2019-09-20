package com.appnio.readinglist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.appnio.readinglist.R
import com.appnio.readinglist.db.Book


class BookAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val HEADER_TYPE = 0
        const val BOOK_TYPE = 1
    }

    var books: List<Book> = arrayListOf()
    var listener: ClickListener? = null

    fun setBookItems(newBooks: List<Book>) {
        books = newBooks
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> HEADER_TYPE
            else -> BOOK_TYPE
        }
    }

    fun setOnItemClickListener(listener: ClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HEADER_TYPE -> HeaderItemViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_header,
                    parent,
                    false
                )
            )
            else -> BookItemViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_book,
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemCount() = books.size + 1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is BookItemViewHolder -> holder.bind(books[position - 1], listener)
        }
    }

    class BookItemViewHolder(val parent: View) : RecyclerView.ViewHolder(parent) {

        val title: TextView = parent.findViewById(R.id.item_book_title)
        val author: TextView = parent.findViewById(R.id.item_book_author)
        val year: TextView = parent.findViewById(R.id.item_book_year)

        fun bind(book: Book, clickListener: ClickListener?) {
            title.text = book.title
            author.text = book.author
            year.text = book.year
            parent.setOnClickListener { clickListener?.onItemClick(book) }
        }
    }

    class HeaderItemViewHolder(parent: View) : RecyclerView.ViewHolder(parent) {

    }

    interface ClickListener {
        fun onItemClick(book: Book)
    }
}