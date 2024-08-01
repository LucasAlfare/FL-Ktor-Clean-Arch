@file:Suppress("PropertyName", "SpellCheckingInspection")

val ktor_version: String by project
val exposed_version: String by project

plugins {
  kotlin("jvm")
  id("org.jetbrains.kotlin.plugin.serialization")
}

group = "com.lucasalfare"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
}

dependencies {
  // Ktor (base and engine)
  implementation("io.ktor:ktor-server-core:$ktor_version")
  implementation("io.ktor:ktor-server-netty:$ktor_version")

  // Serialization
  implementation("io.ktor:ktor-server-content-negotiation:$ktor_version")
  implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")

  // SQL Framework Exposed Core, JDBC transport layer
  implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
  implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")

  // Specific SQLite driver dependency (just for quick test/debug)
  implementation("org.xerial:sqlite-jdbc:3.45.2.0")

  // Logger for Exposed and Ktor
  implementation("ch.qos.logback:logback-classic:1.5.3")

  // Dependencies used only in tests
  testImplementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
  testImplementation("io.ktor:ktor-server-test-host:$ktor_version")
  testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
  useJUnitPlatform()
}

kotlin {
  jvmToolchain(17)
}