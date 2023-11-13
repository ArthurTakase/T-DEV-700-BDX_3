package api

import io.javalin.Javalin
import kotlin.test.Test
import kotlin.test.assertNotNull

class AppTest {
    @Test
    fun serverStartsAndHasPort() {
        val app = Javalin.create().start(0) // démarrer sur un port aléatoire
        try {
            assertNotNull(app.port())
        } finally {
            app.stop()
        }
    }
}
