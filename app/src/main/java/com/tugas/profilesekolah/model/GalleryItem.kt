package com.tugas.profilesekolah.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "item")
@Parcelize
data class GalleryItem (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @Json(name = "urlgambar")
    val urlgambar: String?,
    @Json(name = "title")
    val title: String?,
    @Json(name = "desc")
    val desc: String?,
    var type: String?
) : Parcelable

@Parcelize
data class PrestasiItem(
    @Json(name = "title")
    val title: String?,
    @Json(name = "data")
    val data : List<GalleryItem> = arrayListOf()
): Parcelable