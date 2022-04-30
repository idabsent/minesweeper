plugins {
    kotlin("jvm") version "1.6.20"
    application
}

group   = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

application {
    mainClass.set("minesweeper.MainKt")
}

val run: JavaExec by tasks
run.standardInput = System.`in`