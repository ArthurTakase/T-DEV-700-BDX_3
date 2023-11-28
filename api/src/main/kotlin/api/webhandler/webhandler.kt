import io.javalin.http.Context

class WebHandler()
{
    fun exec(ctx: Context)
    {
        try {
            call(ctx)
        }
        catch(e: Exception) {
            ctx.status(501)
        }
    }

    fun call(ctx: Context)
    {
        print("CALL WEBHANDLER")
        ctx.result("Hello world!!!!!!!!!!!")
    }
}