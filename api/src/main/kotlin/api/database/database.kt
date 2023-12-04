import com.github.jasync.sql.db.QueryResult
import com.github.jasync.sql.db.postgresql.PostgreSQLConnectionBuilder
import java.util.concurrent.CompletableFuture

// "jdbc:postgresql://$host:$port/$database?user=$username&password=$password"
class Database(databasePath: String) {
    private var connection = PostgreSQLConnectionBuilder.createConnectionPool(databasePath)

    fun execQuery(query: String): CompletableFuture<QueryResult> {
        return connection.sendPreparedStatement(query)
    }
}
