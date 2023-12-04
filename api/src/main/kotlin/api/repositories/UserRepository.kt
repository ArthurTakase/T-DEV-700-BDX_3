

class UserRepository(database: Database) {
    private val database = database

    init {}

    fun all() {
        print(database.execQuery("select * from USERS").get())
    }

    fun create() {}

    fun findById() {}

    fun update() {}

    fun delete() {}
}
