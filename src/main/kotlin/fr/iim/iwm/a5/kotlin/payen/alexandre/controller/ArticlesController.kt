package fr.iim.iwm.a5.kotlin.payen.alexandre.controller

import io.ktor.freemarker.FreeMarkerContent
import io.ktor.html.HtmlContent
import io.ktor.http.Parameters


interface ArticlesController {
    fun getList(isAuth : Boolean): FreeMarkerContent
    fun getSingle(id: Int, isAuth : Boolean): Any
    fun addComment(postParameters: Parameters)
}