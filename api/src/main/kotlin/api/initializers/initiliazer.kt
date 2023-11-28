class Initializer() {
    public var database =
            Database("jdbc:postgresql://db:5432/dev_pg_db_cm?user=postgres&password=postgres")
    public var webHandler = WebHandler()

    init {
        val migrationUsers = MigrationUsers(database)
        migrationUsers.exec()
        val migrationAccounts = MigrationAccounts(database)
        migrationAccounts.exec()
        print("In initializer")
    }
}
