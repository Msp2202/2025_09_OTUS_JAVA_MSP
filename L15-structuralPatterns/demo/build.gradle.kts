dependencies {
    implementation ("ch.qos.logback:logback-classic")
}
// Отключить spotless для demo модуля
tasks.named("spotlessCheck") {
    enabled = false
}

tasks.named("spotlessApply") {
    enabled = false
}