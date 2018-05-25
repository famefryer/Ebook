package com.example.fame.ebook

import android.net.sip.SipSession
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() ,Observer{

    override fun update(p0: Observable?, p1: Any?) {
        rv_bookList.adapter = BookAdapter(bookRepo.getBooks(),this)
        status.setText("All books : "+bookRepo.bookList.size)
    }
    val bookRepo = BookListRepository()
//    val present = BookPresenter(this,bookRepo)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bookRepo.addObserver(this)
//        present.start()
//        showBook()
        bookRepo.loadAllBooks()
//        searchview.setOnQueryTextListener()
        searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
                searchBook(searchview.query.toString())
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                searchBook(query.toString())
                return true
            }
        })
        searchview.setOnSearchClickListener(View.OnClickListener {
            rv_bookList.adapter = BookAdapter(bookRepo.getBooks(),this)
            status.setText("All books : "+bookRepo.getBooks().size)
        })
        rv_bookList.layoutManager = LinearLayoutManager(this)
        rv_bookList.adapter = BookAdapter(bookRepo.getBooks(),this)

    }

    fun searchBook(input:String){
        if(input.equals("")){
            rv_bookList.adapter = BookAdapter(bookRepo.getBooks(),this)
            status.setText("All books : "+bookRepo.getBooks().size)
            return
        }
        var newList:ArrayList<Book> = bookRepo.searchByNameAndYear(input)
        rv_bookList.adapter = BookAdapter(newList,this)
        status.setText("Search Result : "+newList.size+" for "+input)
    }

//    override fun setBookList(books: ArrayList<Book>) {
//        var bookString = ""
//        books.forEach {
//            bookString += it.toString()
//        }
//        list.text = bookString
//    }


}
