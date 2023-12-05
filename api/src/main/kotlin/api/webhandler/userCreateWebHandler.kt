import io.javalin.http.Context

class UserCreateWebHandler(val userRepository: UserRepository) : WebHandler() {

    override fun call(ctx: Context) {
        val userData = ctx.bodyAsClass(User::class.java)

        val name = userData?.name
        val email = userData?.email

        if (name.isNotEmpty() && email.isNotEmpty()) {
            val user = userRepository.create(name, email)

            if (user != null) {
                ctx.status(201).json(user)
            } else {
                ctx.status(500).json("Error creating user")
            }
        } else {
            ctx.status(400).json("Missing data")
        }
    }
}
