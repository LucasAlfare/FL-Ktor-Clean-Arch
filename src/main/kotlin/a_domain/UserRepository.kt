package a_domain

interface UserRepository {
  suspend fun create(name: String, age: Int): Int {
    println("Creating user using ${this.javaClass.simpleName}")
    return -1
  }

  suspend fun getById(id: Int): User? {
    println("Creating user using ${this.javaClass.simpleName}")
    return null
  }
}