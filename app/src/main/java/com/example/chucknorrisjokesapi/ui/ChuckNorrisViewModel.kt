package com.example.chucknorrisjokesapi.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chucknorrisjokesapi.network.ChuckNorrisApi
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
        viewModelScope.launch{
            try {
                val response = jokeService.getRandomJoke("people")
                if (response.isSuccessful) {
                    _joke.value = response.body()?.result
                } else {
                    Log.e("ChuckNorrisViewModel", "Error fetching joke")
                }
            } catch (e: Exception) {
                Log.e("ChuckNorrisViewModel", "Error fetching joke: ${e.message}")
            }
        }
    }
}