plugins {
    id("java")
    id("application")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

application {
    mainClass.set("ru.otus.HelloOtus")
}

// Вместо стандартного jar используем shadowJar
tasks.shadowJar {
    archiveFileName = "hw01-gradle-fat.jar"
}