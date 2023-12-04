
import com.github.jasync.sql.db.general.ArrayRowData
class UserRepository(database: Database) {
    private val database = database

    init {}

    fun all() {
        val result = database.execQuery("select * from USERS").get()
        print((result.rows!![0] as ArrayRowData).columns.toList())
    }

    fun create() {}

    fun findById() {}

    fun update() {}

    fun delete() {}
}
