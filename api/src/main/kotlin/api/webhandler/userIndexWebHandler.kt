import io.javalin.http.Context

class UserIndexWebHandler(userRepository: UserRepository) : WebHandler() {
    val userRepository = userRepository

    override fun call(ctx: Context) {
        println(userRepository)
        userRepository.all()
        ctx.result("Hello world from user!!!!!!!!!!!")
    }
}
