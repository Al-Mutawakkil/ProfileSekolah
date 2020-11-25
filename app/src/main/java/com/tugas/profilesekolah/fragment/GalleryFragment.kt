package com.tugas.profilesekolah.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.tugas.profilesekolah.LoadingDialog
import com.tugas.profilesekolah.R
import com.tugas.profilesekolah.adapter.GalleryAdapter
import com.tugas.profilesekolah.model.GalleryItem
import com.tugas.profilesekolah.network.RetrofitInterface
import com.tugas.profilesekolah.network.RetrofitService
import com.tugas.profilesekolah.room.RoomDB
import kotlinx.android.synthetic.main.fragment_gallery.view.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch


class GalleryFragment : Fragment() {

    private lateinit var adapter: GalleryAdapter
    private lateinit var loadingDialog: LoadingDialog
    private lateinit var roomDB: RoomDB

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_gallery, container, false)

        loadingDialog = LoadingDialog(view.context, container!!)

        adapter = GalleryAdapter()

        roomDB = RoomDB.getInstance(view.context)

        view.rv_list_galeri.adapter = adapter
        view.rv_list_galeri.setHasFixedSize(true)
        view.rv_list_galeri.layoutManager = LinearLayoutManager(context)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadingDialog.startAlertDialog()

        val dataItemGallery = roomDB.roomDao().getDataByType("Gallery")
//        dataItemGallery.observe(viewLifecycleOwner, Observer {
//            Log.e("Banyak data", it.size.toString())
//            if (it.isNotEmpty){
//                adapter.addData(it)
//                adapter.notifyDataSetChanged()
//                loadingDialog.stopDialog()
//            }else {
//                Toast.makeText(context, "Database Kosong", Toast.LENGTH_SHORT).show()
//            }
//        })


        val retrofitService = RetrofitService.buildService(RetrofitInterface::class.java)

        viewLifecycleOwner.lifecycleScope.launch {
            val requestData = retrofitService.getDataGallery()

            if (requestData.isSuccessful){
                val dataGallery = requestData.body() as List<GalleryItem>
                dataGallery.forEach{
                    it.type = "Gallery"
                }
                roomDB.roomDao().insertData(dataGallery)
            }
        }
    }
}


