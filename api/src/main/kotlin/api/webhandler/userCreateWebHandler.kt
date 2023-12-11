import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.javalin.http.Context

data class UserForm(val email: String, val name: String)

class UserCreateWebHandler(val userRepository: UserRepository) : WebHandler() {
    override fun call(ctx: Context) {
        val objectMapper = jacksonObjectMapper()
        val userObj = objectMapper.readTree(ctx.body()).get("user")
        val userData = objectMapper.readValue(userObj.toString(), UserForm::class.java)

        val name = userData.name
        val email = userData.email

        if (name.isNullOrEmpty() || email.isNullOrEmpty()) {
            ctx.status(400).json("Missing data")
        } else {
            val user = userRepository.create(name, email)

            if (user != null) {
                ctx.status(201).json(user)
            } else {
                ctx.status(500).json("Error creating user")
            }
        }
    }
}
