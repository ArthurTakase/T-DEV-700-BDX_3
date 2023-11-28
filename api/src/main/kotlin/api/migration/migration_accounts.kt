

class MigrationAccounts(database: Database)
{
    private val database = database

    fun exec()
    {
        database.execQuery("CREATE TABLE IF NOT EXISTS accounts (id SERIAL PRIMARY KEY, account_number INTEGER, sold INTEGER);")
        print("Migration accounts")
    }
}