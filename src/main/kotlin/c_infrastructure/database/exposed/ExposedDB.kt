package c_infrastructure.database.exposed

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object ExposedDB {

  val myDb by lazy { Database.connect(url = "jdbc:sqlite:./data.db", driver = "org.sqlite.JDBC") }

  fun initDatabase() {
    transaction(myDb) {
      SchemaUtils.createMissingTablesAndColumns(UsersTable)
    }
  }
}