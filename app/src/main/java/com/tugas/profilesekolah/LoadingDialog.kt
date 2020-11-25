package com.tugas.profilesekolah

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup

class LoadingDialog(context: Context, viewGroup: ViewGroup) {

    private val inflater = LayoutInflater.from(context)
    private val xmlLoading = inflater.inflate(R.layout.loading_alert, viewGroup, false)

    private lateinit var dialog: AlertDialog

    private val myContext = context

    fun startAlertDialog() {
        if (xmlLoading.parent ==  null){
            val alertDialog = AlertDialog.Builder(myContext)
            alertDialog.setView(xmlLoading)
            alertDialog.setCancelable(false)
            dialog = alertDialog.create()
        }
        dialog.show()
    }

    fun stopDialog() {
        dialog.dismiss()
    }
}