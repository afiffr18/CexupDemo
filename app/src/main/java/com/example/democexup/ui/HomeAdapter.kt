package com.example.democexup.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.democexup.R
import com.example.democexup.local.Entities.Dosen
import com.example.democexup.local.Entities.relations.MatakuliahDanDosen
import com.google.android.material.card.MaterialCardView

class HomeAdapter(private val onClick : (dosen : Dosen) -> Unit) : RecyclerView.Adapter<HomeAdapter.HomeAdapterViewHolder>() {

    private val diffcallback = object : DiffUtil.ItemCallback<MatakuliahDanDosen>(){
        override fun areItemsTheSame(
            oldItem: MatakuliahDanDosen,
            newItem: MatakuliahDanDosen
        ): Boolean {
            return oldItem.matakuliah.id == newItem.matakuliah.id
        }

        override fun areContentsTheSame(
            oldItem: MatakuliahDanDosen,
            newItem: MatakuliahDanDosen
        ): Boolean {
            return oldItem.matakuliah.id.hashCode() == newItem.matakuliah.id.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this,diffcallback)

    fun updateDataHome(matakuliahDanDosen: List<MatakuliahDanDosen>) =differ.submitList(matakuliahDanDosen)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_mata_kuliah,parent,false)
        return HomeAdapterViewHolder(view)
    }

    override fun getItemCount() = differ.currentList.size

    override fun onBindViewHolder(holder: HomeAdapterViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    inner class HomeAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val namaMakul = itemView.findViewById<TextView>(R.id.tv_nama_produk)
        private val itemclick = itemView.findViewById<MaterialCardView>(R.id.card_makul)
        fun bind(matakuliahDanDosen: MatakuliahDanDosen){
            namaMakul.text = matakuliahDanDosen.matakuliah.nama
            itemclick.setOnClickListener {
                onClick.invoke(matakuliahDanDosen.dosen)
            }
        }
    }
}