package com.notesappify.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.notesappify.data.dao.NotesDao
import com.notesappify.data.models.Notes
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

    private val _searchedNotes = MutableStateFlow(listOf<Notes>())
    val searchedNotes = _searchedNotes.asStateFlow()


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

    fun setSubtitle(subtitle: String) {
        _notesValidation.update {
            it.copy(subtitle = subtitle)
        }
    }

    fun setDescription(description: String) {
        _notesValidation.update {
            it.copy(description = description)
        }
    }

    fun setNewTime() {
        _notesValidation.update {
            it.copy(registerTime = System.currentTimeMillis())
        }
    }

    fun enableButton(): Boolean {
        val notes = _notesValidation.value
        return notes.title.isNotBlank() && notes.description.isNotBlank()
    }

    fun searchCoincidences(query: String) {
        viewModelScope.launch {
            dao.searchNoteCoincidences(query).collect { notes ->
                _searchedNotes.value = notes
            }
        }
    }

}