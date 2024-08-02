package c_infrastructure.webserver.ktor

import a_domain.Constants
import a_domain.WebserverLauncher
import io.ktor.server.engine.*
import io.ktor.server.netty.*

object KtorLauncher : WebserverLauncher {
  override suspend fun start(args: Array<String>) {
    super.start(args)
    embeddedServer(Netty, port = Constants.BACKEND_API_PORT) {
      configureSerialization()
      configureRouting()
    }.start(wait = true)
  }
}