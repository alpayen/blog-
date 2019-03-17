package fr.iim.iwm.a5.kotlin.payen.alexandre

import fr.iim.iwm.a5.kotlin.payen.alexandre.models.Article
import io.ktor.auth.UserPasswordCredential
import io.ktor.http.Parameters

interface Model {
    fun getArticleList(): List<Article>
    fun getArticle(id: Int): Article?
    fun addArticle(postParameters: Parameters): Int
    fun deleteArticle(id: Int)
    fun addComment(postParameters: Parameters)
    fun deleteComment(id: Int)
    fun checkAuth(userPasswordCredential: UserPasswordCredential): Boolean
}