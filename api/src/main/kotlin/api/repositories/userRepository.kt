

class UserRepository(database: Database) {
    private val database = database

    init {}

    fun all(): List<User> {
        val result = database.execQuery("SELECT * FROM USERS").get()
        val users =
            result.rows.map { row ->
                UserParser(row).parse()
            }
        println(users)
        return users
    }

    fun create(
        name: String,
        email: String,
    ): User? {
        val query = "INSERT INTO users (name, email) VALUES (?, ?) RETURNING *"
        val args = listOf(name, email)

        val result = database.execQuery(query, args).get()

        if (result.rowsAffected.equals(0)) {
            return null
        }

        val user =
            result.rows.map { row ->
                UserParser(row).parse()
            }.firstOrNull()
        return user
    }

    fun findById() {}

    fun update() {}

    fun delete() {}
}
