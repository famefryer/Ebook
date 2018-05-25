package com.example.fame.ebook

import java.util.*

class BookPresenter(val ba: BookAdapter,
                    val repository: BookRepository): Observer {
    fun start() {
        repository.addObserver(this)
        repository.loadAllBooks()
    }

    override fun update(obj: Observable?, arg: Any?) {
        if(obj == repository) {
            ba.setBookList(repository.getBooks())
        }
    }
}