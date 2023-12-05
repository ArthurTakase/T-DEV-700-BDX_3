import io.javalin.http.Context

class UserIndexWebHandler(userRepository: UserRepository) : WebHandler() {
    val userRepository = userRepository

    override fun call(ctx: Context) {
        println(userRepository)
        val users = userRepository.all()
        ctx.json(users)
    }
}
