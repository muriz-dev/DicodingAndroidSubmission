package com.example.dicodingandroidsubmission

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Book(
    val title: String,
    val author: String,
    val description: String,
    val cover: String
) : Parcelable
