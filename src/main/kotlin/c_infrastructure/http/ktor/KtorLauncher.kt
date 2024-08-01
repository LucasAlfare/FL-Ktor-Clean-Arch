package c_infrastructure.http.ktor

import io.ktor.server.engine.*
import io.ktor.server.netty.*

object KtorLauncher {

  fun start() {
    embeddedServer(Netty, port = 80) {
      configureSerialization()
      configureRouting()
    }.start()
  }
}