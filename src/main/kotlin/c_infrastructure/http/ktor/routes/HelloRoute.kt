package c_infrastructure.http.ktor.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.helloRoute() {
  get("/hello") {
    call.respondText("Hello from Ktor!")
  }
}