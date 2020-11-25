package com.tugas.profilesekolah.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.tugas.profilesekolah.LoadingDialog
import com.tugas.profilesekolah.R
import com.tugas.profilesekolah.adapter.GalleryAdapter
import com.tugas.profilesekolah.model.GalleryItem
import com.tugas.profilesekolah.network.RetrofitInterface
import com.tugas.profilesekolah.network.RetrofitService
import kotlinx.android.synthetic.main.fragment_ekskul.view.*
import kotlinx.coroutines.launch

class EkskulFragment : Fragment() {

    private lateinit var adapter: GalleryAdapter
    private lateinit var loadingDialog: LoadingDialog

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_ekskul, container, false)

        loadingDialog = LoadingDialog(view.context, container!!)
        adapter = GalleryAdapter()

        view.list_ekskul.adapter = adapter
        view.list_ekskul.setHasFixedSize(true)
        view.list_ekskul.layoutManager = LinearLayoutManager(context)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadingDialog.startAlertDialog()
        val retrofitService = RetrofitService.buildService(RetrofitInterface::class.java)

        viewLifecycleOwner.lifecycleScope.launch {
            val requestData = retrofitService.getDataEkskul()

            if (requestData.isSuccessful){
                val dataEkskul = requestData.body() as List<GalleryItem>
                adapter.addData(dataEkskul)
                adapter.notifyDataSetChanged()
                loadingDialog.stopDialog()
            }
        }
    }

}