import java.net.URI

include(":shared-data-source")


include(":sold-properties")


pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = URI("https://jitpack.io") }
    }
}

rootProject.name = "Kotlin-Tryouts"
include(":app")
include(":kotlin-bread-jams")
include(":crypto")
include(":stateflow-sharedflow")
include(":mockito")
include(":sample-xml-app")
include(":work-manager-example")
include(":job-scheduler-example")
include(":bound-service-example")
include(":rx-android-jav")
include(":prod-flavours-test")
include(":koin-test")
include(":multithread-test")
include(":bitcoinj-utils")
include(":clean-arch")
include(":parcelize_test")
include(":problem-solving-questions")
include(":canvas-samples")
include(":saved-state-handle")
include(":typesafe-nv")
include(":hilt-example")
include(":data-store")
include(":broadcast-play")
include(":eventbus-example")
include(":hilt-example-2")
include(":multiscreen")
include(":turbine-tests")
include(":alarm-manager")
