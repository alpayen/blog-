package fr.iim.iwm.a5.kotlin.payen.alexandre.controller

import io.ktor.freemarker.FreeMarkerContent
import io.ktor.html.HtmlContent
import io.ktor.http.Parameters


interface ArticlesAdminController {
    fun deleteArticle(id: Int)
    fun addArticle(postParameters: Parameters) : Int
    fun deleteComment(id: Int)
}