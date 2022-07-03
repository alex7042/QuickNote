package com.example.quicknote.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.quicknote.models.AppNote

@Dao
interface AppRoomDao{

    @Query("SELECT * from notes_table")
    fun getAllNotes():LiveData<List<AppNote>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: AppNote)

    @Delete
    suspend fun delete(note: AppNote)
}