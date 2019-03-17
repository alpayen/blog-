package fr.iim.iwm.a5.kotlin.payen.alexandre

import fr.iim.iwm.a5.kotlin.payen.alexandre.models.Article
import fr.iim.iwm.a5.kotlin.payen.alexandre.models.Comment
import io.ktor.application.ApplicationCall
import io.ktor.auth.UserPasswordCredential
import io.ktor.http.Parameters
import javafx.application.Application
import org.mindrot.jbcrypt.BCrypt
import java.sql.Statement


class MysqlModel : Model {

    private val connectionPool = ConnectionPool("jdbc:mysql://localhost:3306/blog_kotlin", "root", "root")

    override fun getArticleList(): ArrayList<Article> {
        val articles = ArrayList<Article>()
        connectionPool.use { connection ->
            connection.prepareStatement("SELECT id,title FROM articles").use { statement ->
                val results = statement.executeQuery()
                while (results.next()) articles += Article(
                    results.getInt("id"),
                    results.getString("title")
                )
            }
        }
        return articles
    }

    override fun getArticle(id: Int): Article? {

        connectionPool.use { connection ->
            connection.prepareStatement("SELECT * FROM articles WHERE id = ?").use { statement ->
                statement.setInt(1, id)
                val results = statement.executeQuery()
                val found = results.next()
                if (found) {
                    val comments = ArrayList<Comment>()
                    connection.prepareStatement("SELECT * FROM comments WHERE article_id = ?").use { commentStatement ->
                        commentStatement.setInt(1, id)
                        val resultComments = commentStatement.executeQuery()

                        while (resultComments.next()) comments += Comment(
                            resultComments.getInt("id"),
                            resultComments.getInt("article_id"),
                            resultComments.getString("comment")
                        )
                    }

                    return Article(
                        results.getInt("id"),
                        results.getString("title"),
                        results.getString("text"),
                        comments
                    )
                }
            }
        }
        return null
    }

    override fun addArticle(postParameters: Parameters): Int {
        connectionPool.use { connection ->
            connection.prepareStatement(
                "INSERT INTO articles (title, text) VALUES(?, ?)",
                Statement.RETURN_GENERATED_KEYS
            ).use { statement ->
                statement.setString(1, postParameters["title"])
                statement.setString(2, postParameters["text"])
                statement.executeUpdate()
                val keys = statement.generatedKeys
                var id = 0
                while (keys.next()) {
                    println(keys.toString())
                    id = keys.getInt(1)
                    println(id)
                }
                return id
            }
        }
        return 0
    }

    override fun deleteArticle(id: Int) {
        connectionPool.use { connection ->
            connection.prepareStatement("DELETE FROM articles WHERE id = ? ").use { statement ->
                statement.setInt(1, id)
                statement.execute()
            }
        }
    }

    override fun deleteComment(id: Int) {
        connectionPool.use { connection ->
            connection.prepareStatement("DELETE FROM comments WHERE id = ? ").use { statement ->
                statement.setInt(1, id)
                statement.execute()
            }
        }
    }

    override fun addComment(postParameters: Parameters) {
        connectionPool.use { connection ->
            connection.prepareStatement("INSERT INTO comments (article_id, comment) value (?, ?)").use { statement ->
                statement.setInt(1, postParameters["article_id"]!!.toInt())
                statement.setString(2, postParameters["comment"])
                statement.execute()
            }
        }
    }

    override fun checkAuth(userPasswordCredential: UserPasswordCredential): Boolean {
        connectionPool.use { connection ->
            connection.prepareStatement("SELECT * FROM admins WHERE username = ?").use { statement ->
                statement.setString(1, userPasswordCredential.name)
                val res = statement.executeQuery()
                if(res.next() && BCrypt.checkpw(userPasswordCredential.password, res.getString("pass")))
                    return true
            }
        }
        return false
    }
}