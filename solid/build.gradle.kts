
plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
dependencies {
    testImplementation("io.mockk:mockk:1.13.11")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
}
