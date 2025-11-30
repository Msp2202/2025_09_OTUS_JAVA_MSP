dependencies {
    implementation ("ch.qos.logback:logback-classic")
}
tasks.named("sonarlintMain") {
    enabled = false
}