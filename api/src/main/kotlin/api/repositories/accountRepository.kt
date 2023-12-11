class AccountRepository(database: Database) {
    private val database = database

    init {}

    fun get(id: Int): Account? {
        val result = database.execQuery("SELECT * FROM ACCOUNTS WHERE id=$id").get()
        val account = result.rows.map { row -> AccountParser(row).parse() }.firstOrNull()
        return account
    }
}
