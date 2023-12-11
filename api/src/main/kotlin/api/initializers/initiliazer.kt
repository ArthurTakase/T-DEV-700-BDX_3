class Initializer() {
    public var database =
        Database("jdbc:postgresql://db:5432/dev_pg_db_cm?user=postgres&password=postgres")
    public var userRepository = UserRepository(database)
    public var accountRepository = AccountRepository(database)
    public var userIndexWebHandler = UserIndexWebHandler(userRepository)
    public var userCreateWebHandler = UserCreateWebHandler(userRepository)
    public var accountHasMoneyWebHandler = AccountHasMoneyWebHandler(accountRepository)

    init {
        val migrationUsers = MigrationUsers(database)
        val migrationAccounts = MigrationAccounts(database)

        migrationUsers.exec()
        migrationAccounts.exec()

        print("In initializer")
    }
}
