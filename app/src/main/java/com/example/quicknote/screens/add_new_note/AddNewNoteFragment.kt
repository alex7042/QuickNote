package com.example.quicknote.screens.add_new_note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.quicknote.R
import com.example.quicknote.databinding.FragmentAddNewNoteBinding
import com.example.quicknote.models.AppNote
import com.example.quicknote.utilities.APP_ACTIVITY
import com.example.quicknote.utilities.showToast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AddNewNoteFragment : Fragment() {

    private var _binding: FragmentAddNewNoteBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: AddNewNoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddNewNoteBinding.inflate(inflater)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {
        mViewModel = ViewModelProvider(this)[AddNewNoteViewModel::class.java]
        mBinding.btnAddNote.setOnClickListener{
            val name = mBinding.inputNameNote.text.toString()
            val text = mBinding.inputTextNote.text.toString()
            if (name.isEmpty()){
                showToast(getString(R.string.toast_enter_name))
            }else{
                mViewModel.insert(AppNote(name = name, text = text)){
                    mViewModel.viewModelScope.launch(Dispatchers.Main){
                        APP_ACTIVITY.mNavController.navigate(R.id.action_addNewNoteFragment_to_mainFragment)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}