package com.example.dicodingandroidsubmission

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide


class Profile : AppCompatActivity(), View.OnClickListener {
    private lateinit var userPhoto: ImageView
    private lateinit var btnGithub: Button
    private lateinit var btnLinkedin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.profile)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        userPhoto = findViewById(R.id.img_user_photo)
        btnGithub = findViewById(R.id.btn_github)
        btnLinkedin = findViewById(R.id.btn_linkedin)

        btnGithub.setOnClickListener(this)
        btnLinkedin.setOnClickListener(this)

        Glide.with(this).load(getString(R.string.user_photo)).into(userPhoto)
    }

    override fun onClick(v: View?) {
        val intent = Intent()
        intent.setAction(Intent.ACTION_VIEW)
        intent.addCategory(Intent.CATEGORY_BROWSABLE)

        when(v?.id) {
            R.id.btn_github -> {
                intent.setData(Uri.parse("https://github.com/muriz-dev"))
                startActivity(intent)
            }

            R.id.btn_linkedin -> {
                intent.setData(Uri.parse("https://www.linkedin.com/in/muhamadrizki-dev/"))
                startActivity(intent)
            }
        }
    }
}