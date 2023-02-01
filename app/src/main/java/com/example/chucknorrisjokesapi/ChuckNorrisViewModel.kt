package com.example.chucknorrisjokesapi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chucknorrisjokesapi.data.ChuckNorrisModel
import com.example.chucknorrisjokesapi.data.JokesResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class ChuckNorrisViewModel @Inject constructor(private val jokeService: ChuckNorrisApi) :
    ViewModel() {
    private val _joke = MutableLiveData<List<JokesResult>>()
    val joke: LiveData<List<JokesResult>>
        get() = _joke

    fun fetchJoke() {
        viewModelScope.launch {
            val response = jokeService.getRandomJoke("people")
            if (response.isSuccessful) {
                _joke.value = response.body()?.result
            }
        }
    }
}