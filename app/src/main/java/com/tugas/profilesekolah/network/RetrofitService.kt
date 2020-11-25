package com.tugas.profilesekolah.network

import com.tugas.profilesekolah.model.FounderItem
import com.tugas.profilesekolah.model.GalleryItem
import com.tugas.profilesekolah.model.PrestasiItem
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface RetrofitInterface{
    @GET("Data/Gallery.json")
    suspend fun getDataGallery() : Response<List<GalleryItem>>

    @GET("Data/Ekskul.json")
    suspend fun getDataEkskul() : Response<List<GalleryItem>>

    @GET("Data/Prestasi.json")
    suspend fun getDataPrestasi() : Response<List<PrestasiItem>>

    @GET("Data/Founder.json")
    suspend fun getDataFounder() : Response<FounderItem>
}

object RetrofitService {
    // fungsi HttpLoggingInterceptor adalah mengecek status response dari server
    // keterangan status server di LogCat :
    // 200 = respose sukses
    // 404 = url not found
    // 401 = tidak ada otorisasi / API Key belum dimasukkan
    // 500 = Masalah di server
    private fun interceptor(): Interceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        return interceptor
    }

    // client bertugas sebagai alat penghubung ke server
    // di sini tempat kita memasukkan Interceptor
    private val client = OkHttpClient.Builder()
        .addInterceptor(interceptor())
        .build()

    // retrofit bertugas sebagai pengatur client
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://profilsekolah-8315c.firebaseio.com/") // masukkan baseUrl
        .addConverterFactory(MoshiConverterFactory.create())
        .client(client)
        .build()

    fun <T> buildService(service: Class<T>): T {
        return retrofit.create(service)
    }
}