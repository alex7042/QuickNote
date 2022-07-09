package com.example.quicknote.data

import androidx.lifecycle.LiveData
import com.example.quicknote.models.AppNote

class AppRoomRepository(private val appRoomDao: AppRoomDao):DatabaseRepository {

    override val allNotes: LiveData<List<AppNote>>
    get() = appRoomDao.getAllNotes()

    override suspend fun insert(note: AppNote, onSuccess: ()->Unit) {
        appRoomDao.insert(note)
        onSuccess()
    }

    override suspend fun delete(note: AppNote, onSuccess: ()->Unit) {
        appRoomDao.delete(note)
        onSuccess()
    }

    override suspend fun update(note: AppNote, onSuccess: () -> Unit) {
        appRoomDao.update(note)
        onSuccess()
    }
}