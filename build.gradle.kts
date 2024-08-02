@file:Suppress("PropertyName", "SpellCheckingInspection")

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


val ktor_version: String by project
val exposed_version: String by project

plugins {
  id("org.springframework.boot") version "3.1.2"
  id("io.spring.dependency-management") version "1.1.2"
  kotlin("jvm") version "2.0.0" // The version of Kotlin to use
  kotlin("plugin.spring") version "2.0.0" // The Kotlin Spring plugin
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

//  implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
  implementation("org.springframework.boot:spring-boot-starter-webflux")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin") // Jackson extensions for Kotlin for working with JSON
  implementation("org.jetbrains.kotlin:kotlin-reflect") // Kotlin reflection library, required for working with Spring

  // Logger for Exposed and Ktor
  implementation("ch.qos.logback:logback-classic")

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

tasks.withType<KotlinCompile> { // Settings for `KotlinCompile` tasks
  compilerOptions { // Kotlin compiler options
    freeCompilerArgs.add("-Xjsr305=strict")// = listOf("-Xjsr305=strict") // `-Xjsr305=strict` enables the strict mode for JSR-305 annotations
  }
}