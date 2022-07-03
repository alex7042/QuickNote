package com.example.quicknote.screens.note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.quicknote.models.AppNote
import com.example.quicknote.utilities.REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteFragmentViewModel(application: Application):AndroidViewModel(application) {

    fun delete(note: AppNote, onSuccess:()->Unit)=
        viewModelScope.launch(Dispatchers.Main){
            REPOSITORY.delete(note){
                onSuccess()
            }
        }
}