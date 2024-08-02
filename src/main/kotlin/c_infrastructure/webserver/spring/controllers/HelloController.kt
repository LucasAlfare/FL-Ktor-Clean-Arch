package c_infrastructure.webserver.spring.controllers

import a_domain.Constants
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class HelloController {
  @GetMapping(Constants.HELLO_ROUTE_URL)
  suspend fun helloRoute(): String {
    return "Hello, from Spring!"
  }
}