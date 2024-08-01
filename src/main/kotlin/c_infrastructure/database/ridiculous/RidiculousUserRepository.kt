package c_infrastructure.database.ridiculous

import a_domain.User
import a_domain.UserRepository
import kotlin.math.abs
import kotlin.random.Random

object RidiculousUserRepository : UserRepository {

  private val users = mutableListOf<User>()

  override suspend fun create(name: String, age: Int): Int {
    val nextUser = User(
      id = abs(Random.nextInt()),
      name = name,
      age = age
    )

    users += nextUser

    return nextUser.id
  }

  override suspend fun getById(id: Int): User? {
    return users.singleOrNull { it.id == id }
  }
}