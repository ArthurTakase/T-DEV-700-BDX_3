/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package cashmanager.main

import io.javalin.Javalin

fun main() {
    val app = Javalin.create().start(8080)
    app.get("/") { ctx -> ctx.result("Hello World") }
}