package fr.iim.a5.kotlin.payen.alexandre



import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import fr.iim.iwm.a5.kotlin.payen.alexandre.models.Article
import fr.iim.iwm.a5.kotlin.payen.alexandre.Model
import fr.iim.iwm.a5.kotlin.payen.alexandre.controller.ArticlesControllerImpl
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.HttpStatusCode
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ArticleTest {


    @Test
    fun testMdelArticleFound() {
        val model = mock<Model>{
            on {getArticle(1) } doReturn Article(1, "Titre")
        }
        val ctrl = ArticlesControllerImpl(model)
        val result = ctrl.getSingle(1)
        assertTrue(result is FreeMarkerContent)


    }
    @Test
    fun testMdelArticleNotFound(){
        val model = mock<Model>{}
        val ctrl = ArticlesControllerImpl(model)
        val result = ctrl.getSingle(-1)

        assertEquals(HttpStatusCode.NotFound, result)
    }



}