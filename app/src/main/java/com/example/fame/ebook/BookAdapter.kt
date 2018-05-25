package com.example.fame.ebook
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created by fame on 25/5/2561.
 */
class BookAdapter(var books:ArrayList<Book>,val context:Context) : RecyclerView.Adapter<BookViewHolder>(){
    fun setBookList(list:ArrayList<Book>){
        books = list
    }
    override fun onBindViewHolder(holder: BookViewHolder?, position: Int) {
        holder?.setView(books.get(position))
    }

    override fun getItemCount(): Int {
        return books.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BookViewHolder {
        return BookViewHolder(LayoutInflater.from(context).inflate(R.layout.book_item,parent,false))
    }

}

class BookViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    var book_name : TextView = view.findViewById(R.id.tv_bookname)
    var book_year : TextView = view.findViewById(R.id.tv_bookYear)
    var book_price :TextView = view.findViewById(R.id.tv_bookprice)
    fun setView(book:Book){
        book_name.setText(book.title)
        book_price.setText("Price : "+book.price)
        book_year.setText("Publication Year : "+book.publicationYear)
    }
}