package a_domain

interface UserRepository {
  suspend fun create(name: String, age: Int): Int

  suspend fun getById(id: Int): User?
}