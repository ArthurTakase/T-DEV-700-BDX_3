class UserRepository(database: Database) {
    private val database = database

    init {}

    fun all(): List<User> {
        val result = database.execQuery("SELECT * FROM USERS").get()
        val users = result.rows.map { row -> UserParser(row).parse() }
        println(users)
        return users
    }

    fun create(
        name: String,
        email: String,
        last_key: String,
        password: String,
    ): User? {
        val passwordHashed = sha256(password)
        val query = "INSERT INTO users (name, email, last_key, password) VALUES (?, ?, ?, ?) RETURNING *"
        val args = listOf(name, email, last_key, passwordHashed)

        val result = database.execQuery(query, args).get()

        if (result.rowsAffected.equals(0)) {
            return null
        }

        val user = result.rows.map { row -> UserParser(row).parse() }.firstOrNull()
        return user
    }

    fun findById() {}

    fun update() {}

    fun delete() {}

    private fun sha256(input: String): String {
        val message = input.toByteArray()
        val digest = java.security.MessageDigest.getInstance("SHA-256").digest(message)

        val hexChars = CharArray(digest.size * 2)
        for (i in digest.indices) {
            val v = digest[i].toInt() and 0xFF
            hexChars[i * 2] = "0123456789ABCDEF"[v ushr 4]
            hexChars[i * 2 + 1] = "0123456789ABCDEF"[v and 0x0F]
        }

        return String(hexChars)
    }
}
