rootProject.name = "2025_09_OTUS_JAVA_MSP"

include("hw01-gradle")

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }

    plugins {
        id("io.spring.dependency-management") version "1.1.7"
        id("org.springframework.boot") version "3.4.2"
    }
}
