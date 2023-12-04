
import com.github.jasync.sql.db.general.ArrayRowData
import java.time.OffsetDateTime

class UserRepository(database: Database) {
    private val database = database

    init {}

    fun all() {
        val result = database.execQuery("select * from USERS").get()
        val users = result.rows.map { row ->
             UserParser(
                id = row.get("id") as Int,
                name = row.get("name") as String,
                created_at = row.get("created_at") as OffsetDateTime,
                updated_at = row.get("updated_at") as OffsetDateTime
            ).parse()
        }
        println(users)
    }

    fun create() {}

    fun findById() {}

    fun update() {}

    fun delete() {}
}
