package com.example.quicknote.room

import androidx.lifecycle.LiveData
import com.example.quicknote.models.AppNote

interface DatabaseRepository {
    val allNotes:LiveData<List<AppNote>>
    suspend fun insert(note: AppNote, onSuccess:()->Unit)
    suspend fun delete(note: AppNote, onSuccess:()->Unit)
}