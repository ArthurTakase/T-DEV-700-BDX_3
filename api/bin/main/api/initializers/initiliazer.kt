class Initializer() {
    public var database = Database("jdbc:postgresql://db:5432/dev_pg_db_cm?user=postgres&password=postgres")

    init {
        val migrationUsers = MigrationUsers(database)
        migrationUsers.exec()
        print("In initializer")
    }
}
