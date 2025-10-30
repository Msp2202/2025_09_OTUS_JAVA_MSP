/* Настройка корневого проекта и включение модулей */
rootProject.name = "2025_09_OTUS_JAVA_MSP"

/* Включение подпроектов в сборку */
include("hw01-gradle")  /* Модуль с первым домашним заданием */
//include("L02-gradle2")
//include("L02-gradle2-libApi")
//include("L02-gradle2-libApiUse")
//include("L02-logging")
//include("L03-qa")
//include("L04-generics")
//include("L05-collections")
//include("L06-annotations")

/* Управление версиями плагинов через settings */
pluginManagement {
    /* Чтение версий плагинов из gradle.properties */
    val jgitver: String by settings
    val dependencyManagement: String by settings
    val springframeworkBoot: String by settings
    val johnrengelmanShadow: String by settings
    val jib: String by settings
    val protobufVer: String by settings
    val sonarlint: String by settings
    val spotless: String by settings

    /* Настройка репозиториев для плагинов */
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }

    /* Регистрация плагинов с версиями */
    plugins {
//        id("fr.brouillard.oss.gradle.jgitver") version jgitver
        id("io.spring.dependency-management") version dependencyManagement
        id("org.springframework.boot") version springframeworkBoot
        id("com.github.johnrengelman.shadow") version johnrengelmanShadow
//        id("com.google.cloud.tools.jib") version jib
//        id("com.google.protobuf") version protobufVer
//        id("name.remal.sonarlint") version sonarlint
//        id("com.diffplug.spotless") version spotless
    }
}