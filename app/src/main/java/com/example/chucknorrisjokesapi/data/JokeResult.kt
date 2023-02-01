package com.example.chucknorrisjokesapi.data

data class JokesResult(
    val categories: List<String>,
    val created_at: String,
    val icon_url: String,
    val id: String,
    val updated_at: String,
    val url: String,
    val value: String?
)