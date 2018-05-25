package com.example.fame.ebook

import android.os.AsyncTask
import com.beust.klaxon.JsonReader
import com.beust.klaxon.Klaxon
import java.io.StringReader
import java.net.URL
import java.util.ArrayList

class BookListRepository : BookRepository() {

    var bookList = ArrayList<Book>()
    val bl = BookLoader()
    override fun getBooks(): ArrayList<Book> {
        return bookList
    }

    override fun loadAllBooks() {
        bookList.clear()
        bl.execute()
    }

    override fun searchByNameAndYear(input: String): ArrayList<Book> {
        var selectedBook : ArrayList<Book> = ArrayList(bookList.filter {
            it.title.toLowerCase().contains(input.toLowerCase()) || it.publicationYear.toString().equals(input)
        }.toSortedSet(Comparator({book1,book2 -> book1.title.compareTo(book2.title)})))
        return selectedBook
    }

    inner class BookLoader : AsyncTask<String,Integer,String>(){
        override fun doInBackground(vararg p0: String?): String {
            var url = URL("https://theory.cpe.ku.ac.th/~jittat/courses/sw-spec/ebooks/books.json")
            return url.readText()
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            if (result != null){
                val klaxon = Klaxon()
                JsonReader(StringReader(result)).use { reader ->
                    reader.beginArray {
                        while (reader.hasNext()) {
                            klaxon.parse<Book>(reader)?.let { bookList.add(it) }
                        }
                    }
                }
            }
            setChanged()
            notifyObservers()
        }
    }
}
