package com.notesappify.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.notesappify.data.Notes
import com.notesappify.data.NotesDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(private val dao: NotesDao) : ViewModel() {

    private val _notesValidation = MutableStateFlow(Notes())
    val notesValidation = _notesValidation.asStateFlow()

    private val _message = MutableSharedFlow<String>()
    val message = _message.asSharedFlow()

    val getNotes = dao.getAllNotes()


    fun insertNote() {
        viewModelScope.launch {
            dao.insertNote(_notesValidation.value)
            _message.emit("registro realizado con éxito")
            _notesValidation.value = Notes()
        }
    }

    fun deleteNote(note: Notes) {
        viewModelScope.launch {
            dao.deleteNote(note)
            _message.emit("registro eliminado con éxito")
        }
    }

    fun setNote(id: Int) {
        viewModelScope.launch {
            _notesValidation.value = dao.getCurrentNote(id)
        }
    }

    fun updateNote() {
        viewModelScope.launch {
            dao.updateNote(_notesValidation.value)
            _message.emit("registro modificado con exito")
        }
    }

    fun setTitle(title: String) {
        _notesValidation.update {
            it.copy(title = title)
        }
    }

    fun setDescription(description: String) {
        _notesValidation.update {
            it.copy(description = description)
        }
    }

    fun enableButton(): Boolean {
        val notes = _notesValidation.value
        return notes.title.isNotBlank() && notes.description.isNotBlank()
    }

}