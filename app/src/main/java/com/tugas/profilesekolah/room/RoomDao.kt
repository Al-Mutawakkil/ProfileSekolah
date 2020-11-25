package com.tugas.profilesekolah.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.tugas.profilesekolah.model.GalleryItem

@Dao
interface RoomDao {

    @Query("SELECT * FROM item WHERE type = :type")
    fun getDataByType(type: String)

    @Query("DELETE FROM item")
    suspend fun resetDatabase()

    @Query("DELETE FROM item WHERE type = :type")
    suspend fun resetType(type: String)

    @Insert
    suspend fun insertData(data: List<GalleryItem>)

    @Delete
    suspend fun removeData(data: GalleryItem)
}