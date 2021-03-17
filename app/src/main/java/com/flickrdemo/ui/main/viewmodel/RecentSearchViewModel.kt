package com.flickrdemo.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flickrdemo.data.repository.Repository
import com.flickrdemo.database.RecentSearchWord
import kotlinx.coroutines.launch

class RecentSearchViewModel(
    private val repository: Repository, ) : ViewModel() {


    private suspend fun insert(recentSearchWord: RecentSearchWord) {
        repository.insertRecentSearchKeyword(recentSearchWord)
    }

    fun saveRecentSearchKeyword(keyword: String) {
        viewModelScope.launch {

            val recentSearchWord = RecentSearchWord()
            recentSearchWord.recentSearchKeyword = keyword
            insert(recentSearchWord)

        }
    }


}