package c_infrastructure.http.ktor.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import userService

fun Route.getUserRoute() {
  get("/users/{id}") {
    val userId = call.parameters["id"]!!
    val searchedUser = userService.getUserById(userId.toInt())
    if (searchedUser != null) {
      call.respond(HttpStatusCode.OK, searchedUser)
    } else {
      call.respond(HttpStatusCode.NotFound)
    }
  }
}