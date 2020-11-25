package com.tugas.profilesekolah.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.tugas.profilesekolah.LoadingDialog
import com.tugas.profilesekolah.R
import com.tugas.profilesekolah.model.FounderItem
import com.tugas.profilesekolah.network.RetrofitInterface
import com.tugas.profilesekolah.network.RetrofitService
import kotlinx.android.synthetic.main.fragment_founder.*
import kotlinx.coroutines.launch

class FounderFragment : Fragment() {

    private lateinit var loadingDialog: LoadingDialog

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_founder, container, false)

        loadingDialog = LoadingDialog(view.context, container!!)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val retrofitService = RetrofitService.buildService(RetrofitInterface::class.java)

        loadingDialog.startAlertDialog()

        viewLifecycleOwner.lifecycleScope.launch {
            val requestFounder = retrofitService.getDataFounder()

            if (requestFounder.isSuccessful){
                val dataFounder = requestFounder.body() as FounderItem

                Glide.with(this@FounderFragment).load(dataFounder.gambarProfil).into(imageView)

                txt_nama_founder.text = dataFounder.nama
                desc_about.text = dataFounder.about
                loadingDialog.stopDialog()
            }
        }
    }
}
