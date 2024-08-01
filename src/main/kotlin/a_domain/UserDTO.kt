package a_domain

import kotlinx.serialization.Serializable

@Serializable
data class UserDTO(
  val name: String,
  val age: Int
) {
  init {
    require(name.isNotEmpty())
    require(age in 0..100)
  }
}