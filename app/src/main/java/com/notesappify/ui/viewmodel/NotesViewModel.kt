package com.notesappify.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.notesappify.data.Notes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NotesViewModel: ViewModel() {

    private val _notesValidation = MutableStateFlow(Notes())
    val notesValidation = _notesValidation.asStateFlow()

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