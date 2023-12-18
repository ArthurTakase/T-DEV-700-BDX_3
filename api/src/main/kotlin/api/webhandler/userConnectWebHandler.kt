import io.javalin.http.Context

class UserConnectWebHandler(userRepository: UserRepository) : WebHandler() {
    val userRepository = userRepository

    override fun call(ctx: Context) {
        ctx.json("testKey")
    }
}
