package c_infrastructure.webserver.ktor.routes

import a_domain.Constants
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.helloRoute() {
  get(Constants.HELLO_ROUTE_URL) {
    call.respondText("Hello from Ktor!")
  }
}