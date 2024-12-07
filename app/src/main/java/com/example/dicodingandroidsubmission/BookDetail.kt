package com.example.dicodingandroidsubmission

import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide

class BookDetail : AppCompatActivity() {
    companion object {
        const val EXTRA_BOOK = "extra_book"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_book_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.book_detail)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val imgCover: ImageView = findViewById(R.id.img_cover)
        val tvTitle: TextView = findViewById(R.id.tv_title)
        val tvAuthor: TextView = findViewById(R.id.tv_author)
        val tvStar: TextView = findViewById(R.id.tv_star)
        val tvRating: TextView = findViewById(R.id.tv_rating)
        val tvReviewer: TextView = findViewById(R.id.tv_reviewer)
        val tvDescription: TextView = findViewById(R.id.tv_description)
        val tvGenre: TextView = findViewById(R.id.tv_genre)

        val book = if (Build.VERSION.SDK_INT >= 33){
            intent.getParcelableExtra<Book>(EXTRA_BOOK, Book::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Book>(EXTRA_BOOK)
        }

        Glide.with(this).load(book?.cover).into(imgCover)
        tvTitle.text = book?.title.toString()
        tvAuthor.text = book?.author.toString()
        tvStar.text = book?.star.toString()
        tvRating.text = book?.rating.toString()
        tvReviewer.text = book?.reviewer.toString()
        tvDescription.text = book?.description.toString()
        tvGenre.text = book?.genre.toString()
    }
}