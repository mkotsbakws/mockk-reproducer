plugins {
    kotlin("jvm") version "1.9.22"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("io.kotest:kotest-framework-api:5.8.0")
    testImplementation("io.kotest:kotest-runner-junit5:5.8.0")
    testImplementation("io.mockk:mockk:1.13.12")
    testImplementation("org.amshove.kluent:kluent:1.73")
}

tasks.test {
    useJUnitPlatform {
        includeEngines("kotest")
    }
}
kotlin {
    jvmToolchain(17)
}
