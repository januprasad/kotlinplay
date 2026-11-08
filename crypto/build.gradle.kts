plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

dependencies {
    implementation("org.bouncycastle:bcprov-jdk15on:1.70")
}
dependencies {
    implementation("org.bouncycastle:bcprov-jdk15on:1.70")
}
