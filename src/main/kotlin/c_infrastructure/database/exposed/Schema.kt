package c_infrastructure.database.exposed

import org.jetbrains.exposed.dao.id.IntIdTable

object UsersTable : IntIdTable("UsersTable") {
  val name = text("name")
  val age = integer("age")
}
