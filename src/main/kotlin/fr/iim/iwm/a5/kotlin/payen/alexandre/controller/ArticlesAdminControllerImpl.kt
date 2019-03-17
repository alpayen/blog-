package fr.iim.iwm.a5.kotlin.payen.alexandre.controller

import fr.iim.iwm.a5.kotlin.payen.alexandre.Model
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.HttpStatusCode
import io.ktor.http.Parameters


class ArticlesAdminControllerImpl(private val model: Model) : ArticlesAdminController {
    override fun addArticle(postParameters: Parameters): Int {
        return model.addArticle(postParameters)
    }

    override fun deleteArticle(id: Int) {
        model.deleteArticle(id)
    }

    override fun deleteComment(id: Int) {
        model.deleteComment(id)
    }
}