package com.tugas.profilesekolah.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tugas.profilesekolah.model.FounderItem
import com.tugas.profilesekolah.model.GalleryItem


@Database(entities = [GalleryItem::class, FounderItem::class], version = 1, exportSchema = false)

abstract class RoomDB: RoomDatabase() {
    abstract fun roomDao(): RoomDao

    companion object{
        private var instance: RoomDB? = null

        fun getInstance(context: Context): RoomDB {
            return instance?: synchronized(this){
                instance ?: Room.databaseBuilder(context, RoomDB::class.java, "item.db")
                    .fallbackToDestructiveMigration()
                    .build()
            }
        }
    }
}