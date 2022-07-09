package com.example.quicknote.screens.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.quicknote.utilities.REPOSITORY

class MainFragmentViewModel(application: Application):AndroidViewModel(application) {
    val allNotes = REPOSITORY.allNotes

}