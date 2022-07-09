package com.example.quicknote.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.quicknote.models.AppNote

@Database(entities = [AppNote::class], version = 1)
abstract class AppRoomDatabase:RoomDatabase() {

    abstract fun getAppRoomDao():AppRoomDao
    // чтобы не создалось две базы данных
    companion object{

        @Volatile // чтобы не кэшировать, а всегда получать актуальные значения
        private var database: AppRoomDatabase? = null

        @Synchronized // запрещает, чтобы к этой функции обращались несколько экземпляров одновременно
        fun getInstance(context: Context): AppRoomDatabase{ // предоставление экземпляра БД
            return if (database==null){
                database = Room.databaseBuilder(
                    context,
                    AppRoomDatabase::class.java,
                    "database"
                ).build()
                database as AppRoomDatabase
            }else database as AppRoomDatabase
        }
    }
}