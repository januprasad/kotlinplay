# Kotlin-Tryouts 🚀

Welcome to **Kotlin-Tryouts**, a comprehensive playground and collection of Android & Kotlin sub-projects, architectural samples, Jetpack integrations, concurrency experiments, and modern development best practices.

---

## 📂 Repository Module Directory

This repository is organized into over 35 modular sub-projects, each exploring specific Kotlin or Android concepts:

### 🏛️ Architecture & Design Patterns
- **[mvi-sample](mvi-sample/README.md)**: Model-View-Intent (MVI) architecture sample app featuring unidirectional data flow, immutable states, intents, side effects, and biometric authentication.
- **[clean-arch](clean-arch/README.md)**: Clean Architecture implementation separating domain, data, and presentation layers.
- **[userlist](userlist/README.md)**: User list implementation demonstrating modern Jetpack Compose UI with Hilt and ViewModels.

### ⚡ Concurrency, Coroutines & Flow
- **[coroutines](coroutines/README.md)**: Kotlin Coroutines fundamentals, structured concurrency, and builders.
- **[turbine-tests](turbine-tests/README.md)**: Flake-free Flow testing using CashApp Turbine, `StandardTestDispatcher`, and sync points (`runCurrent()`).
- **[stateflow-sharedflow](stateflow-sharedflow/README.md)**: Hot flows exploration comparing `StateFlow` and `SharedFlow` behavior.
- **[multithread-test](multithread-test/README.md)**: Multi-threading experiments and synchronization utilities in Kotlin.

### 💉 Dependency Injection & Build
- **[hilt-example](hilt-example/README.md)** & **[hilt-example-2](hilt-example-2/README.md)**: Hilt dependency injection setup, custom scopes, and view model injection.
- **[koin-test](koin-test/README.md)**: Lightweight Koin dependency injection configuration and testing.
- **[typesafe-nv](typesafe-nv/README.md)**: Type-safe project accessors and Gradle version catalog configuration.

### 📱 Android Jetpack & UI Components
- **[canvas-samples](canvas-samples/README.md)**: Advanced Jetpack Compose Canvas drawing, graphics, and custom UI rendering.
- **[data-store](data-store/README.md)**: Jetpack Preferences and Proto DataStore implementation.
- **[saved-state-handle](saved-state-handle/README.md)**: State persistence across process death using SavedStateHandle.
- **[parcelize_test](parcelize_test/README.md)**: `@Parcelize` serialization experiments.
- **[sample-xml-app](sample-xml-app/README.md)**: Legacy XML Views interoperability and migration examples.

### ⚙️ System, Services & Background Work
- **[alarm-manager](alarm-manager/README.md)**: Scheduling alarms and handling alarms in Android.
- **[bound-service-example](bound-service-example/README.md)**: Bound services and client-service IPC communication.
- **[broadcast-play](broadcast-play/README.md)**: Broadcast Receivers and dynamic intent filtering.
- **[job-scheduler-example](job-scheduler-example/README.md)**: Background job scheduling using JobScheduler.
- **[work-manager-example](work-manager-example/README.md)**: Jetpack WorkManager for robust, constraint-based background work.
- **[handler-looper](handler-looper/README.md)**: Android `Handler`, `Looper`, and message queue mechanisms.

### 🛠️ Utilities, Crypto & Testing
- **[crypto](crypto/README.md)**: Pure Kotlin cryptographic utilities and hashing functions.
- **[mockito](mockito/README.md)**: Mockito unit testing integration and mocking patterns.
- **[eventbus-example](eventbus-example/README.md)**: EventBus messaging pattern implementation.
- **[multiscreen](multiscreen/README.md)**: Multi-window and screen adaptation experiments.
- **[prod-flavours-test](prod-flavours-test/README.md)**: Product flavors and build variant configuration.
- **[shared-data-source](shared-data-source/README.md)**: Shared code and data sources across modules.
- **[sold-properties](sold-properties/README.md)**: Kotlin property delegation and custom delegates.
- **[stackoverflow](stackoverflow/README.md)**: Solutions and reproductions for common StackOverflow questions.
- **[kotlin-bread-jams](kotlin-bread-jams/README.md)** & **[kotlin-tryout](kotlin-tryout/README.md)**: General Kotlin language experiments and snippet tests.
- **[problem-solving-questions](problem-solving-questions/README.md)**: Algorithmic and problem-solving exercises in Kotlin.

---

## 🛠️ Requirements & Setup

- **Android Studio**: Ladybug / Jellyfish or newer
- **JDK**: Java 11 / Java 17 / Java 21 (configured per module via toolchain/compileOptions)
- **Gradle**: 8.x with Version Catalogs (`gradle/libs.versions.toml`)

Feel free to explore any module to see focused implementations of specific Android and Kotlin features!
