package fr.iim.iwm.a5.kotlin.payen.alexandre.models

data class Article(val id: Int, val title: String, val text: String? = null, val comments : ArrayList<Comment>? = ArrayList())
