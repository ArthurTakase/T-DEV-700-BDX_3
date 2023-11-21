class Initializer() {
    public var database = Database("jdbc:postgresql://db:5432/dev_pg_db_cm?user=postrgres&password=postgres")

    init {
        print("In initializer")
    }
}
