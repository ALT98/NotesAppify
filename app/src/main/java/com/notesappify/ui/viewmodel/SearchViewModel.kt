package com.notesappify.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.notesappify.data.dao.SearchDao
import com.notesappify.data.models.Search
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val dao: SearchDao) : ViewModel() {

    private val _search = MutableStateFlow(Search())
    val search = _search.asStateFlow()

    val latestSearches = dao.getLatestSearches()

    fun insertSearch() {
        viewModelScope.launch {
            dao.insertQuery(_search.value)
        }
    }

    fun setParam(search: String) {
        _search.update { it.copy(param = search) }
    }

    fun setNewTime() {
        _search.update { it.copy(registerTime = System.currentTimeMillis()) }
    }
}