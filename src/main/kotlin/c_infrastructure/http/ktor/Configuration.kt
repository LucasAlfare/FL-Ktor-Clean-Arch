package c_infrastructure.http.ktor

import c_infrastructure.http.ktor.routes.createUserRoute
import c_infrastructure.http.ktor.routes.getUserRoute
import c_infrastructure.http.ktor.routes.helloRoute
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json

fun Application.configureSerialization() {
  install(ContentNegotiation) {
    json(Json { isLenient = false })
  }
}

fun Application.configureRouting() {
  routing {
    helloRoute()
    createUserRoute()
    getUserRoute()
  }
}