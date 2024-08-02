package c_infrastructure.webserver.spring

import a_domain.Constants
import a_domain.WebserverLauncher
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.server.WebServerFactoryCustomizer
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory
import org.springframework.context.annotation.Bean

object SpringLauncher : WebserverLauncher {
  @SpringBootApplication
  private class ProjectApp {
    @Bean
    fun customSpringOptions(): WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {
      return WebServerFactoryCustomizer { factory ->
        factory.setPort(Constants.BACKEND_API_PORT)
      }
    }
  }

  override suspend fun start(args: Array<String>) {
    super.start(args)
    runApplication<ProjectApp>(*args)
  }
}