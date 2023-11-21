

class MigrationUsers(database: Database)
{
    private val database = database

    fun exec()
    {
        database.execQuery("CREATE TABLE IF NOT EXISTS users (id SERIAL PRIMARY KEY, name VARCHAR(255));")
        print("Migration users")
    }
}