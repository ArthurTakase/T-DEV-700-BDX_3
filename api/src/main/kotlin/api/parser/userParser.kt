import java.time.OffsetDateTime

class UserParser(
    val id: Int,
    val name: String,
    val created_at: OffsetDateTime,
    val updated_at: OffsetDateTime,
) {
    fun parse(): User {
        return User(id = id, name = name, created_at = created_at, updated_at = updated_at)
    }
}
