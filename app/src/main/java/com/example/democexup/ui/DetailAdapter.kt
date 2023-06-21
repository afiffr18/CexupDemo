package com.example.democexup.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.democexup.R
import com.example.democexup.local.Entities.Mahasiswa
import com.example.democexup.local.Entities.relations.MatakuliahDenganMahasiswa
import com.google.android.material.imageview.ShapeableImageView

class DetailAdapter : RecyclerView.Adapter<DetailAdapter.DetailViewHolder>() {

    var diffcallback = object : DiffUtil.ItemCallback<Mahasiswa>() {
        override fun areItemsTheSame(
            oldItem: Mahasiswa,
            newItem: Mahasiswa
        ): Boolean {
            return oldItem.nim == newItem.nim
        }

        override fun areContentsTheSame(
            oldItem: Mahasiswa,
            newItem: Mahasiswa
        ): Boolean {
            return oldItem.nim.hashCode() == newItem.nim.hashCode()
        }

    }

    var differ = AsyncListDiffer(this,diffcallback)

    fun updateMahasiswa(mahasiswa: List<Mahasiswa>) = differ.submitList(mahasiswa)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_mahasiswa,parent,false)
        return DetailViewHolder(view)
    }

    override fun getItemCount() = differ.currentList.size

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    inner class DetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nim = itemView.findViewById<TextView>(R.id.tv_nim)
        private val nama = itemView.findViewById<TextView>(R.id.tv_mahasiswa)
        private val ivImage = itemView.findViewById<ShapeableImageView>(R.id.iv_person)
        private val bgOptions = RequestOptions().placeholder(R.drawable.baseline_person_24)
        fun bind(mahasiswa: Mahasiswa){
            nim.text = mahasiswa.nim
            nama.text = mahasiswa.nama
//            Glide.with(itemView.context)
//                .load("https://st3.depositphotos.com/1017228/18878/i/600/depositphotos_188781580-stock-photo-handsome-cheerful-young-man-standing.jpg")
//                .apply(bgOptions).into(ivImage)
        }
    }
}