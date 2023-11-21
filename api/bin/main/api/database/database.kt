import com.github.jasync.sql.db.postgresql.PostgreSQLConnectionBuilder

// "jdbc:postgresql://$host:$port/$database?user=$username&password=$password"
class Database(databasePath: String) {
    private var connection = PostgreSQLConnectionBuilder.createConnectionPool(databasePath)

    fun execQuery(query: String) {
        connection.sendPreparedStatement(query)
    }
}
