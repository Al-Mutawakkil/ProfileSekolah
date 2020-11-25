package com.tugas.profilesekolah.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tugas.profilesekolah.R
import com.tugas.profilesekolah.model.GalleryItem
import kotlinx.android.synthetic.main.list_item_galeri.view.*

class GalleryAdapter: RecyclerView.Adapter<GalleryViewHolder>() {

    private var listItem = arrayListOf<GalleryItem>()
    fun addData(items: List<GalleryItem>){
        listItem.clear()
        listItem.addAll(items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_galeri, parent, false)
        return GalleryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        val data = listItem[position]
        holder.bind(data)

    }

}

class GalleryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    fun bind(item: GalleryItem){
        itemView.run {
            title_list_galeri.text = item.title
            subtitle_list_galeri.text = item.desc
            Glide.with(this).load(item.urlgambar).into(image_list_galeri)
        }
    }
}
