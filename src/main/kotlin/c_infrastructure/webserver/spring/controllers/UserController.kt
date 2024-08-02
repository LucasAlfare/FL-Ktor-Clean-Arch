package c_infrastructure.webserver.spring.controllers

import a_domain.Constants
import a_domain.UserDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import userService

@RestController
@RequestMapping
class UserController {
  @PostMapping(Constants.CREATE_USERS_ROUTE_URL)
  suspend fun createUserRoute(@RequestBody userDTO: UserDTO): ResponseEntity<Int> {
    val id = userService.createUser(userDTO)
    return ResponseEntity(id, HttpStatus.CREATED)
  }

  @GetMapping(Constants.GET_USERS_ROUTE_URL)
  suspend fun getUserRoute(@PathVariable id: Int): ResponseEntity<UserDTO> {
    val searchedUser = userService.getUserById(id)
    return if (searchedUser != null) {
      ResponseEntity(searchedUser, HttpStatus.OK)
    } else {
      ResponseEntity(HttpStatus.NOT_FOUND)
    }
  }
}