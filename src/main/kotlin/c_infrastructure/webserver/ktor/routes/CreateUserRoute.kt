package c_infrastructure.webserver.ktor.routes

import a_domain.Constants
import a_domain.UserDTO
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import userService

fun Route.createUserRoute() {
  post(Constants.CREATE_USERS_ROUTE_URL) {
    val receivedUser = call.receive<UserDTO>()
    val id = userService.createUser(receivedUser)
    call.respond(HttpStatusCode.Created, id)
  }
}