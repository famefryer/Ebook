package com.example.fame.ebook

import java.util.*

abstract class BookRepository : Observable() {

    abstract fun loadAllBooks()
    abstract fun getBooks(): ArrayList<Book>
    abstract fun searchByNameAndYear(name : String):ArrayList<Book>
}