package c_infrastructure.database.exposed

import a_domain.User
import a_domain.UserRepository
import c_infrastructure.database.exposed.ExposedDB.myDb
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

object ExposedUsersRepository : UserRepository {
  override suspend fun create(name: String, age: Int) =
    newSuspendedTransaction(context = Dispatchers.IO, db = myDb) {
      super.create(name, age)

      val result = UsersTable.insertAndGetId {
        it[UsersTable.name] = name
        it[UsersTable.age] = age
      }

      result.value
    }

  override suspend fun getById(id: Int) = newSuspendedTransaction(context = Dispatchers.IO, db = myDb) {
    super.getById(id)

    UsersTable
      .selectAll()
      .where { UsersTable.id eq id }
      .singleOrNull()
      .let {
        if (it != null) {
          User(
            id = id,
            name = it[UsersTable.name],
            age = it[UsersTable.age],
          )
        } else {
          null
        }
      }
  }
}