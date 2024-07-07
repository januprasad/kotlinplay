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
    }
}

rootProject.name = "interview-prep"
include(":app")
include(":solid")
include(":crypto")
include(":stateflow-sharedflow")
include(":mockito")
include(":sample-xml-app")
include(":work-manager-example")
include(":job-scheduler-example")
include(":bound-service-example")
include(":rx-android-jav")
