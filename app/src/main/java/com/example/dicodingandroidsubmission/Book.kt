package com.example.dicodingandroidsubmission

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Book(
    val title: String,
    val author: String,
    val description: String,
    val cover: String,
    val star: String,
    val rating: String,
    val reviewer: String,
    val genre: String
) : Parcelable
