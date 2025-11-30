
plugins {
    id("java")
}

dependencies {
    implementation ("ch.qos.logback:logback-classic")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // Тестовые зависимости
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("org.junit.jupiter:junit-jupiter-params")
    testImplementation("org.assertj:assertj-core")
    testImplementation("org.mockito:mockito-core")
    testImplementation("org.mockito:mockito-junit-jupiter")
}
//tasks.test {
//    testLogging {
//        events("passed", "skipped", "failed")
//        showStandardStreams = true
//        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
//    }
//}



