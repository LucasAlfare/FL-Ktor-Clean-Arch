import a_domain.UserRepository
import a_domain.WebserverLauncher
import b_usecase.UserService
import c_infrastructure.database.exposed.ExposedDB
import c_infrastructure.database.exposed.ExposedUsersRepository
import c_infrastructure.database.ridiculous.RidiculousUserRepository
import c_infrastructure.webserver.ktor.KtorLauncher
import c_infrastructure.webserver.spring.SpringLauncher
import kotlin.random.Random

lateinit var userService: UserService
lateinit var userRepository: UserRepository

/**
 * We are choosing what frameworks and launchers to use
 * randomly 💀
 */
suspend fun main(args: Array<String>) {
  val launcher: WebserverLauncher =
    if (Random.nextBoolean()) KtorLauncher
    else SpringLauncher

  userRepository =
    if (Random.nextBoolean()) {
      ExposedDB.initDatabase()
      ExposedUsersRepository
    } else RidiculousUserRepository

  userService = UserService(userRepository)

  launcher.start(args)
}