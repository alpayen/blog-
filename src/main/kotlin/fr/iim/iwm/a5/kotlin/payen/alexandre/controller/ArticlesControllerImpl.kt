package fr.iim.iwm.a5.kotlin.payen.alexandre.controller

import com.nhaarman.mockitokotlin2.isA
import fr.iim.iwm.a5.kotlin.payen.alexandre.Model
import fr.iim.iwm.a5.kotlin.payen.alexandre.models.AuthenticatedUser
import io.ktor.application.call
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.HttpStatusCode
import io.ktor.http.Parameters
import io.ktor.sessions.get
import io.ktor.sessions.sessions


class ArticlesControllerImpl(private val model: Model) : ArticlesController {
    override fun getList(isAuth: Boolean): FreeMarkerContent {
        val articles = model.getArticleList()
        return FreeMarkerContent("home.ftl", mapOf("articles" to articles, "isAuth" to isAuth))
    }

    override fun getSingle(id: Int, isAuth : Boolean): Any {
        val article = model.getArticle(id)
        println(isAuth)
        return if (article != null)
            FreeMarkerContent("single.ftl", mapOf("article" to article, "isAuth" to isAuth))
        else
            return HttpStatusCode.NotFound
    }

    override fun addComment(postParameters: Parameters) {
        model.addComment(postParameters)
    }
}