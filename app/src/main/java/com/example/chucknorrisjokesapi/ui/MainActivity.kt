package com.example.chucknorrisjokesapi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chucknorrisjokesapi.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapterchuck: JokeAdapter

    private val viewModel: ChuckNorrisViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observeJokeData()
        viewModel.fetchJoke()
        refreshswipe()

    }

    private fun refreshswipe() {
        binding.swipechuck.setOnRefreshListener {
            setupRecyclerView()
            observeJokeData()
            binding.swipechuck.isRefreshing = false
        }
    }

    private fun setupRecyclerView() {
        adapterchuck = JokeAdapter()
        binding.recyclerviewChuckNorris.apply {
            adapter = adapterchuck
            layoutManager = LinearLayoutManager(this@MainActivity)

            setHasFixedSize(true)
        }

    }

    private fun observeJokeData() {
        viewModel.joke.observe(this) { joke ->
            if (joke != null) {
                Log.d("MainActivity", "observeJokeData called, joke list size: ${joke.size}")
                adapterchuck.submitList(joke)
            }
        }
    }
}
