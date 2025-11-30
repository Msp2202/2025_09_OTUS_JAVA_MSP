plugins {
    java
}

dependencies {
    implementation("ch.qos.logback:logback-classic")
}

// Отключить ВСЕ задачи форматирования
tasks.all {
    if (name.startsWith("spotless") || name.contains("Format") || name.contains("format")) {
        enabled = false
    }
}

tasks.named("sonarlintMain") {
    enabled = false
}