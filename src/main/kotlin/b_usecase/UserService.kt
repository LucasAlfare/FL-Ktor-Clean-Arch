package b_usecase

import a_domain.UserDTO
import a_domain.UserRepository

class UserService(var userRepository: UserRepository) {

  suspend fun createUser(request: UserDTO): Int {
    println("Using [${userRepository.javaClass.name}] repository implementation")
    return userRepository.create(request.name, request.age)
  }

  suspend fun getUserById(id: Int): UserDTO? {
    println("Using [${userRepository.javaClass.name}] repository implementation")
    val searchedUser = userRepository.getById(id) ?: return null
    return UserDTO(name = searchedUser.name, searchedUser.age)
  }
}