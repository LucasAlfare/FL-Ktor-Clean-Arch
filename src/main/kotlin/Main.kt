import b_usecase.UserService
import c_infrastructure.database.exposed.ExposedDB
import c_infrastructure.database.exposed.ExposedUsersRepository
import c_infrastructure.database.ridiculous.RidiculousUserRepository
import c_infrastructure.http.ktor.KtorLauncher

val userService = UserService(ExposedUsersRepository)

fun main() {
  ExposedDB.initDatabase()
  KtorLauncher.start()
}