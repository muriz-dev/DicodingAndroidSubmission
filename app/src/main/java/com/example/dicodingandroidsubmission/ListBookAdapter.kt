package com.example.dicodingandroidsubmission

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ListBookAdapter(private val listBook: ArrayList<Book>) : RecyclerView.Adapter<ListBookAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgCover: ImageView = itemView.findViewById(R.id.img_item_cover)
        val tvTitle: TextView = itemView.findViewById(R.id.tv_item_title)
        val tvAuthor: TextView = itemView.findViewById(R.id.tv_item_description)
        val tvStar: TextView = itemView.findViewById(R.id.tv_star)
        val tvReviewer: TextView = itemView.findViewById(R.id.tv_reviewer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_book, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listBook.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, author, description, cover, star, rating, reviewer, genre) = listBook[position]
        Glide.with(holder.itemView.context).load(cover).into(holder.imgCover)
        holder.tvTitle.text = name
        holder.tvAuthor.text = author
        holder.tvStar.text = star
        holder.tvReviewer.text = reviewer

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listBook[holder.adapterPosition]) }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Book)
    }
}