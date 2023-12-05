import com.github.jasync.sql.db.QueryResult
import com.github.jasync.sql.db.postgresql.PostgreSQLConnectionBuilder
import java.util.concurrent.CompletableFuture
import kotlin.collections.listOf

// "jdbc:postgresql://$host:$port/$database?user=$username&password=$password"
class Database(databasePath: String) {
    private var connection = PostgreSQLConnectionBuilder.createConnectionPool(databasePath)

    fun execQuery(query: String, args: List<String> = listOf()): CompletableFuture<QueryResult> {

        if (args.isEmpty()) {
            return connection.sendPreparedStatement(query)
        }
        return connection.sendPreparedStatement(query, args)
    }
}
