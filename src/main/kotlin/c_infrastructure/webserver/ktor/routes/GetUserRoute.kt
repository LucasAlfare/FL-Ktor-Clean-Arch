package c_infrastructure.webserver.ktor.routes

import a_domain.Constants
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import userService

fun Route.getUserRoute() {
  get(Constants.GET_USERS_ROUTE_URL) {
    val userId = call.parameters["id"]!!
    val searchedUser = userService.getUserById(userId.toInt())
    if (searchedUser != null) {
      call.respond(HttpStatusCode.OK, searchedUser)
    } else {
      call.respond(HttpStatusCode.NotFound)
    }
  }
}