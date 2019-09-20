package com.appnio.readinglist_v2

import com.appnio.readinglist_v2.db.Book


class InitialData {

    companion object {
        var data = arrayListOf(
            Book(null, "For Whom the Bell Tolls", "Ernest Hemingway", "1951"),
            Book(null, "War of the Worlds", "H. G. Wells", "1898"),
            Book(null, "The Time Machine", "H. G. Wells", "1897"),
            Book(null, "Alice in Wonderland", "Lewis Carroll", "1865"),
            Book(null, "Nature", "Emerson Ralph Waldo", "1836"),
            Book(null, "Macbeth", "William Shakespeare", "1603"),
            Book(null, "Winnie The Pooh", "A. A. Milne", "1926"),
            Book(null, "Adventures Of Huckleberry Finn", "Mark Twain", "1884"),
            Book(null, "War And Peace", "Leo Tolstoy", "1869"),
            Book(null, "Sense And Sensibility", "Jane Austen", "1811")
        )
    }
}