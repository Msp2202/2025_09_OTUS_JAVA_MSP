dependencies {
    implementation ("ch.qos.logback:logback-classic")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
}
tasks.named("sonarlintMain") {
    enabled = false
}

tasks.named("sonarlintTest") {
    enabled = false
}