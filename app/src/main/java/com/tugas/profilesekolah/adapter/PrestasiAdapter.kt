package com.tugas.profilesekolah.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tugas.profilesekolah.R
import com.tugas.profilesekolah.model.GalleryItem
import com.tugas.profilesekolah.model.PrestasiItem
import kotlinx.android.synthetic.main.fragment_prestasi.view.*
import kotlinx.android.synthetic.main.list_prestasi.view.*

class PrestasiAdapter: RecyclerView.Adapter<PrestasiViewHolder>() {

    private var listItem = arrayListOf<PrestasiItem>()
    fun addData(items: List<PrestasiItem>){
        listItem.clear()
        listItem.addAll(items)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrestasiViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_prestasi, parent, false)
        return PrestasiViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    override fun onBindViewHolder(holder: PrestasiViewHolder, position: Int) {
        val dataPrestasi = listItem[position]
        holder.bind(dataPrestasi)
    }

}

class PrestasiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    fun bind(item: PrestasiItem){
        val adapterPrestasi = GalleryAdapter()
        itemView.run {
            title_list_prestasi.text = item.title
            rv_list_item_prestasi.setHasFixedSize(true)
            rv_list_item_prestasi.layoutManager = LinearLayoutManager(context)
            rv_list_item_prestasi.adapter = adapterPrestasi
        }
        adapterPrestasi.addData(item.data)
        adapterPrestasi.notifyDataSetChanged()

    }
}
