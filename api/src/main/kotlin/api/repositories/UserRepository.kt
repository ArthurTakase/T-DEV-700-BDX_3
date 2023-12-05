
import com.github.jasync.sql.db.general.ArrayRowData
import java.time.OffsetDateTime

class UserRepository(database: Database) {
    private val database = database

    init {}

    fun all() : List<User> {
        val result = database.execQuery("select * from USERS").get()
        val users = result.rows.map { row ->
             UserParser(
                id = row.get("id") as Int,
                name = row.get("name") as String,
                email = row.get("email") as String,
                created_at = row.get("created_at") as OffsetDateTime,
                updated_at = row.get("updated_at") as OffsetDateTime
            ).parse()
        }
        println(users)
        return users
    }

    fun create(name: String, email: String): User? {
        val query = "INSERT INTO users (name, email) VALUES (?, ?)"
        val args = listOf(name, email)

        val result = database.execQuery(query, args).get()
        val user = result.rows.map { row ->
            UserParser(
                id = row.get("id") as Int,
                name = row.get("name") as String,
                email = row.get("email") as String,
                created_at = row.get("created_at") as OffsetDateTime,
                updated_at = row.get("updated_at") as OffsetDateTime
            ).parse()
        }.firstOrNull()
        return user
    }

    fun findById() {}

    fun update() {}

    fun delete() {}
}
