package fr.iim.iwm.a5.kotlin.payen.alexandre

import java.sql.Connection
import java.sql.DriverManager
import java.util.concurrent.ConcurrentLinkedQueue

class ConnectionPool(val url : String?, val user : String?, val password : String?){
    private val list = ConcurrentLinkedQueue<Connection>()

    fun getConnection() =
            list.poll() ?: DriverManager.getConnection(url, user, password)

    fun makeAvailable(connection : Connection) {
        list.add(connection)
    }

    inline fun use(block : (connection : Connection) -> Unit ){
        val connection = getConnection()
        try {
            block(connection)
        }finally {
            makeAvailable(connection)
        }
    }
}