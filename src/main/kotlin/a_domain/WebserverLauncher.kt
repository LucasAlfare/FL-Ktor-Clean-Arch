package a_domain

interface WebserverLauncher {

  suspend fun start(args: Array<String> = arrayOf()) {
    println("Launching ${this.javaClass.simpleName}...")
  }
}