package com.example.dicodingandroidsubmission

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvBooks: RecyclerView
    private val list = ArrayList<Book>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        rvBooks = findViewById(R.id.rv_books)
        rvBooks.setHasFixedSize(true)

        list.addAll(getListBooks())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_profile -> {
                val moveProfileIntent = Intent(this@MainActivity, Profile::class.java)
                startActivity(moveProfileIntent)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun getListBooks(): ArrayList<Book> {
        val dataTitle = resources.getStringArray(R.array.data_title)
        val dataAuthor = resources.getStringArray(R.array.data_author)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataCover = resources.getStringArray(R.array.data_cover_image)
        val dataStar = resources.getStringArray(R.array.data_star)
        val dataRating = resources.getStringArray(R.array.data_rating)
        val dataReviewer = resources.getStringArray(R.array.data_reviewer)
        val dataGenre = resources.getStringArray(R.array.data_genre)
        val listBook = ArrayList<Book>()

        for (i in dataTitle.indices){
            val book = Book(dataTitle[i], dataAuthor[i], dataDescription[i], dataCover[i], dataStar[i], dataRating[i], dataReviewer[i], dataGenre[i])
            listBook.add(book)
        }

        return listBook
    }

    private fun showRecyclerList() {
        rvBooks.layoutManager = GridLayoutManager(this, 2)
        val listBookAdapter = ListBookAdapter(list)
        rvBooks.adapter = listBookAdapter

        listBookAdapter.setOnItemClickCallback(object : ListBookAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Book) {
                onClickedItem(data)
            }
        })
    }

    private fun onClickedItem(book: Book){
        val moveWithObjectIntent = Intent(this@MainActivity, BookDetail::class.java)
        moveWithObjectIntent.putExtra(BookDetail.EXTRA_BOOK, book)
        startActivity(moveWithObjectIntent)
    }
}