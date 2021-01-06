package com.jhoander.hackernews.domain.model

data class Hit(
    val title: String,
    val story_title: String,
    val author: String,
    val created_at: String
)