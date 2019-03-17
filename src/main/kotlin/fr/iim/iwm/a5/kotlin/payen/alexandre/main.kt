package fr.iim.iwm.a5.kotlin.payen.alexandre

import fr.iim.iwm.a5.kotlin.payen.alexandre.controller.*
import fr.iim.iwm.a5.kotlin.payen.alexandre.models.AuthenticatedUser
import freemarker.cache.ClassTemplateLoader
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.auth.*
import io.ktor.freemarker.FreeMarker
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.Parameters
import io.ktor.http.content.*
import io.ktor.request.receiveParameters

import io.ktor.response.respond
import io.ktor.response.respondRedirect
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.sessions.*
import java.io.File

class App

fun Application.cmsApp(
    articlesController: ArticlesController,
    articlesAdminController: ArticlesAdminController
) {

    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(App::class.java.classLoader, "templates")
    }

    install(Sessions) {
        cookie<AuthenticatedUser>("ADMIN_SESSION", SessionStorageMemory())
    }

    install(Authentication) {
        form("admin-auth") {
            userParamName = "username"
            passwordParamName = "password"
            challenge = FormAuthChallenge.Redirect { "/login?message=Nom d'utilisateur ou mot de passe invalide" }
            validate {
                val model = MysqlModel()

                if (model.checkAuth(it)) UserIdPrincipal(it.name) else null
            }
            skipWhen { call -> call.sessions.get<AuthenticatedUser>() != null }

        }
    }


    routing {

        get("/") {
            val isAuth = call.sessions.get<AuthenticatedUser>() != null
            call.respond(articlesController.getList(isAuth))
        }

        get("/articles/{id}") {
            val isAuth = call.sessions.get<AuthenticatedUser>() != null
            call.respond(articlesController.getSingle(call.parameters["id"]!!.toInt(), isAuth))
        }


        post("/articles/{id}/new_comment") {
            val postParameters: Parameters = call.receiveParameters()
            println(postParameters)
            articlesController.addComment(postParameters)
            call.respondRedirect("/articles/" + call.parameters["id"]!!)
        }

        get("/login") {
            if (call.sessions.get<AuthenticatedUser>() == null) {
                val message = call.parameters["message"] ?: ""
                call.respond(FreeMarkerContent("login.ftl", mapOf("message" to message, "isAuth" to false)))
            } else call.respondRedirect("/")
        }

        authenticate("admin-auth") {
            post("/login") {
                val principal = call.authentication.principal<UserIdPrincipal>()
                call.sessions.set(AuthenticatedUser(principal!!.name))
                call.respondRedirect("/")
            }

            get("/new_article") {
                val isAuth = call.sessions.get<AuthenticatedUser>() != null
                call.respond(FreeMarkerContent("add.ftl", mapOf("isAuth" to isAuth)))
            }
            post("/new_article") {
                val postParameters: Parameters = call.receiveParameters()
                val insertedId = articlesAdminController.addArticle(postParameters)
                call.respondRedirect("/articles/$insertedId")
            }

            get("/articles/{id}/delete") {
                articlesAdminController.deleteArticle(call.parameters["id"]!!.toInt())
                call.respondRedirect("/")
            }

            get("/delete_comment/{id}/{parent_id}") {
                articlesAdminController.deleteComment(call.parameters["id"]!!.toInt())
                call.respondRedirect("/articles/" + call.parameters["parent_id"]!!)
            }

            get("/logout"){
                val principal = call.authentication.principal<UserIdPrincipal>()
                call.sessions.clear<AuthenticatedUser>()
                call.respondRedirect("/")
            }
        }
    }
}

fun main() {
    val model = MysqlModel()
    val articlesController = ArticlesControllerImpl(model)
    val articlesAdminController = ArticlesAdminControllerImpl(model)
    embeddedServer(Netty, 8080) { cmsApp(articlesController, articlesAdminController) }.start(true)
}

//kotlin mcv injection de dépendance ktor