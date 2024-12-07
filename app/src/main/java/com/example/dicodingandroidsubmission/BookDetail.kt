package com.example.dicodingandroidsubmission

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide

class BookDetail : AppCompatActivity(), View.OnClickListener {
    private lateinit var title: String
    private lateinit var author: String
    private lateinit var rating: String
    private lateinit var genre: String

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
        val btnShare: Button = findViewById(R.id.btn_share)

        btnShare.setOnClickListener(this)

        val book = if (Build.VERSION.SDK_INT >= 33){
            intent.getParcelableExtra(EXTRA_BOOK, Book::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_BOOK)
        }

        title = book?.title.toString()
        author = book?.author.toString()
        rating = "${book?.star} ${book?.rating}"
        genre = book?.genre.toString()

        Glide.with(this).load(book?.cover).into(imgCover)
        tvTitle.text = book?.title.toString()
        tvAuthor.text = book?.author.toString()
        tvStar.text = book?.star.toString()
        tvRating.text = book?.rating.toString()
        tvReviewer.text = book?.reviewer.toString()
        tvDescription.text = book?.description.toString()
        tvGenre.text = book?.genre.toString()
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_share ->{
                val opening = "Ada satu buku yang belakangan ini tidak bisa aku hentikan untuk tidak menceritakannya kepada setiap orang - dan kamu harus segera membacanya!\n\n"
                val bookContent = "Title: ${title}\nAuthor: ${author}\nRating: ${rating}\nGenre: ${genre}\n\n"
                val closing = "Meski tidak sempurna, buku ini menyimpan pesona tersendiri yang mungkin akan mengejutkanmu."

                val content = opening + bookContent + closing
                val shareBookIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, content)
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(shareBookIntent, null)
                startActivity(shareIntent)
            }
        }
    }
}