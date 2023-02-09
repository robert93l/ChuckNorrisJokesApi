package com.example.chucknorrisjokesapi

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.chucknorrisjokesapi.data.JokesResult

class JokeAdapter() :
    RecyclerView.Adapter<JokeAdapter.JokeViewHolder>() {

    private var jokes = listOf<JokesResult>()

    class JokeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val jokeText: TextView = itemView.findViewById(R.id.joke_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.joke_item, parent, false)
        return JokeViewHolder(view)
    }

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        val currentJoke = jokes[position]
        holder.jokeText.text = currentJoke.value
    }

    override fun getItemCount() = jokes.size

   fun submitList(newJokes: List<JokesResult>) {

        val diffResult = DiffUtil.calculateDiff(JokesDiffUtilCallback(jokes, newJokes))
        jokes = newJokes
        Log.d("JokeAdapter", "submitList called, new jokes list size: ${newJokes.size}")
        diffResult.dispatchUpdatesTo(this)

    }

    class JokesDiffUtilCallback(
        private val oldJokes: List<JokesResult>,
        private val newJokes: List<JokesResult>
    ) : DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldJokes[oldItemPosition].id == newJokes[newItemPosition].id
        }

        override fun getOldListSize(): Int = oldJokes.size
        override fun getNewListSize(): Int = newJokes.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldJokes[oldItemPosition] == newJokes[newItemPosition]
        }
    }

}