plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
// repositories {
//    mavenCentral()
// }

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")

    api("org.bitcoinj:bitcoinj-core:0.15.10") {
        exclude("com.squareup.okhttp3", "okhttp")
        exclude("org.slf4j", "slf4j-api")
    }
    api("com.google.guava:guava:26.0-jre")
    api("org.slf4j:slf4j-nop:1.7.25")

    testImplementation("junit", "junit", "4.12")
}
