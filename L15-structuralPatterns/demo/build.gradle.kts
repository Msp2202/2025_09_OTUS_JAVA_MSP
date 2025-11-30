plugins {
    java
}

dependencies {
    implementation("ch.qos.logback:logback-classic")
}

// Отключить ВСЕ spotless задачи
tasks.withType<com.diffplug.gradle.spotless.SpotlessTask>().configureEach {
    enabled = false
}

// Отключить sonarlint
tasks.named("sonarlintMain") {
    enabled = false
}