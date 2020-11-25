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
import com.tugas.profilesekolah.adapter.PrestasiAdapter
import com.tugas.profilesekolah.model.PrestasiItem
import com.tugas.profilesekolah.network.RetrofitInterface
import com.tugas.profilesekolah.network.RetrofitService
import kotlinx.android.synthetic.main.fragment_prestasi.*
import kotlinx.android.synthetic.main.fragment_prestasi.view.*
import kotlinx.coroutines.launch

class PrestasiFragment : Fragment() {

    private lateinit var adapterPrestasi: PrestasiAdapter
    private lateinit var loadingDialog: LoadingDialog

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_prestasi, container, false)

        loadingDialog = LoadingDialog(view.context, container!!)

        adapterPrestasi = PrestasiAdapter()

        view.rv_prestasi.setHasFixedSize(true)
        view.rv_prestasi.layoutManager = LinearLayoutManager(context)
        view.rv_prestasi.adapter = adapterPrestasi

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadingDialog.startAlertDialog()

        val retrofitService = RetrofitService.buildService(RetrofitInterface::class.java)

        viewLifecycleOwner.lifecycleScope.launch {
            val requestDataPrestasi = retrofitService.getDataPrestasi()

            if (requestDataPrestasi.isSuccessful){
                val dataPrestasi = requestDataPrestasi.body() as List<PrestasiItem>
                adapterPrestasi.addData(dataPrestasi)
                adapterPrestasi.notifyDataSetChanged()
                loadingDialog.stopDialog()
            }
        }
    }

}