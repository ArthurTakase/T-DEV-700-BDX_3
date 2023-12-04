

import java.time.OffsetDateTime
class User(
    val id: Int,
    val name: String,
    val created_at: OffsetDateTime,
    val updated_at: OffsetDateTime)
{
    override fun toString(): String {
        return "User(id=$id, name=$name, created_at=$created_at, updated_at=$updated_at)"
    }
}
