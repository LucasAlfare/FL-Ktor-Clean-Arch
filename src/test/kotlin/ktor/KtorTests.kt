package ktor

import a_domain.UserDTO
import c_infrastructure.database.exposed.ExposedDB
import c_infrastructure.database.exposed.UsersTable
import c_infrastructure.database.ridiculous.RidiculousUserRepository
import c_infrastructure.webserver.ktor.configureRouting
import c_infrastructure.webserver.ktor.configureSerialization
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.testing.*
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import userService
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class KtorTests {

  @BeforeTest
  fun setupTest() {
    ExposedDB.initDatabase()
    userService.userRepository = RidiculousUserRepository
  }

  @AfterTest
  fun dispose() {
    transaction {
      SchemaUtils.drop(UsersTable)
    }
  }

  @Test
  fun testHealth() = testApplication {
    application {
      configureSerialization()
      configureRouting()
    }
    val auxClient = createClient {
      install(ContentNegotiation) {
        json(Json { isLenient = false; })
      }
    }

    val helloResult = auxClient.get("/hello")
    assertEquals(expected = HttpStatusCode.OK, actual = helloResult.status)
  }

  @Test
  fun testCreateUser() = testApplication {
    application {
      configureSerialization()
      configureRouting()
    }
    val auxClient = createClient {
      install(ContentNegotiation) {
        json(Json { isLenient = false; })
      }
    }

    val createResult = auxClient.post("/users/create") {
      contentType(ContentType.Application.Json)

      setBody(
        UserDTO(
          name = "Lucas",
          age = 30
        )
      )
    }

    assertEquals(HttpStatusCode.Created, createResult.status)
  }

  @Test
  fun testGetUserById() = testApplication {
    application {
      configureSerialization()
      configureRouting()
    }
    val auxClient = createClient {
      install(ContentNegotiation) {
        json(Json { isLenient = false; })
      }
    }
    val createResult = auxClient.post("/users/create") {
      contentType(ContentType.Application.Json)

      setBody(
        UserDTO(
          name = "Lucas",
          age = 30
        )
      )
    }
    val receivedId = createResult.body<Int>()
    val getResult = auxClient.get("/users/$receivedId")
    assertEquals(HttpStatusCode.OK, getResult.status)
  }
}