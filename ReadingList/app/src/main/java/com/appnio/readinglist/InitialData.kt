package com.appnio.readinglist_v2

import com.appnio.readinglist.db.Book


class InitialData {

    companion object {
        var data = arrayListOf(
            Book(null, "Ernest Hemingway","For Whom the Bell Tolls",  "1951"),
            Book(null, "H. G. Wells","War of the Worlds",  "1898"),
            Book(null, "H. G. Wells","The Time Machine",  "1897"),
            Book(null, "Lewis Carroll","Alice in Wonderland", "1865"),
            Book(null, "Emerson Ralph Waldo","Nature",  "1836"),
            Book(null, "William Shakespeare","Macbeth", "1603"),
            Book(null, "A. A. Milne","Winnie The Pooh",  "1926"),
            Book(null, "Mark Twain","Adventures Of Huckleberry Finn", "1884"),
            Book(null, "Leo Tolstoy","War And Peace",  "1869"),
            Book(null, "Jane Austen","Sense And Sensibility",  "1811")
        )
    }
}