package com.example.quicknote.screens.start

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.quicknote.room.AppRoomDatabase
import com.example.quicknote.room.AppRoomRepository
import com.example.quicknote.utilities.REPOSITORY
import com.example.quicknote.utilities.TYPE_ROOM

class StartFragmentViewModel(application: Application):AndroidViewModel(application) {

    private val mContext = application

    fun initDatabase(type:String, onSuccess:() -> Unit){
        when(type){
            TYPE_ROOM -> {
                val dao = AppRoomDatabase.getInstance(mContext).getAppRoomDao()
                REPOSITORY = AppRoomRepository(dao)
                onSuccess()
            }

        }

    }
}