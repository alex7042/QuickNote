package com.example.quicknote.screens.note

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.quicknote.R
import com.example.quicknote.databinding.FragmentNoteBinding
import com.example.quicknote.models.AppNote
import com.example.quicknote.utilities.APP_ACTIVITY
import com.example.quicknote.utilities.showToast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class NoteFragment : Fragment() {

    private var _binding: FragmentNoteBinding?=null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: NoteFragmentViewModel
    private lateinit var mCurrentNote: AppNote

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteBinding.inflate(layoutInflater,container,false)
        mCurrentNote = arguments?.getSerializable("note") as AppNote
        mBinding.updateTextNote.setText(mCurrentNote.text)
        mBinding.updateNameNote.setText(mCurrentNote.name)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {
        setHasOptionsMenu(true)
        mViewModel = ViewModelProvider(this)[NoteFragmentViewModel::class.java]
        mBinding.btnUpdateNote.setOnClickListener{
            updateItem()
        }
    }

    private fun updateItem(){
        val currentName = mBinding.updateNameNote.text.toString()
        val currentText = mBinding.updateTextNote.text.toString()
        if (currentName.isEmpty()){
            showToast(getString(R.string.toast_enter_name))
        }else{
            mViewModel.update(AppNote(mCurrentNote.id, name = currentName, text = currentText)){
                mViewModel.viewModelScope.launch(Dispatchers.Main){
                    APP_ACTIVITY.mNavController.navigate(R.id.action_noteFragment_to_mainFragment)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.note_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.btn_delete ->{
                mViewModel.delete(mCurrentNote){
                    APP_ACTIVITY.mNavController.navigate(R.id.action_noteFragment_to_mainFragment)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}