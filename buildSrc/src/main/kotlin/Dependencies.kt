
// Kotlin
private const val kotlinVersion = "1.4.31"
private const val coroutinesVersion = "1.4.2"
private const val kotlinxSerializationVersion = "1.0.1"

// DI
private const val koinVersion = "3.5.6"
private const val koinVMVersion = "3.5.6"

// API - Square
private const val retrofitVersion = "2.9.0"
private const val loggingInterceptorVersion = "4.9.1"
private const val serializationConverterVersion = "0.8.0"

// AndroidX
private const val ktxCoreVersion = "1.3.2"
private const val appCompatVersion = "1.6.1"
private const val fragmentVersion = "1.3.0"
private const val recyclerViewVersion = "1.1.0"
private const val constraintUi = "2.1.4"
private const val navigationVersion = "2.5.3"
private const val lifecycleVersion = "2.3.0"

// Google
private const val materialDesignVersion = "1.5.0"

// Other
private const val timberVersion = "4.7.1"

// Test
private const val jUnitVersion = "4.13.2"
private const val jUnitAndroidVersion = "1.1.5"
private const val mockkVersion = "1.10.6"
private const val espressoVersion = "3.3.0"

// DataBase
private const val room = "2.5.0"
private const val datastore = "1.0.0"

object Dependencies {
    object Kotlin {
        const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"
        const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"
        const val coroutinesAndroid =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"
        const val coroutinesKtx =
            "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:$coroutinesVersion"
        const val kotlinxSerialization =
            "org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinxSerializationVersion"
    }

    object Di {
        const val koin = "io.insert-koin:koin-android:$koinVersion"
        const val koinViewModel = "io.insert-koin:koin-androidx-navigation:$koinVMVersion"
        const val koinAndroid = "io.insert-koin:koin-android:$koinVMVersion"
    }

    object Database {
        private object Versions {
            const val room = "2.5.0"
            const val datastore = "1.0.0-alpha05"
        }

        const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
        const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
        const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
        const val roomTest = "androidx.room:room-testing:${Versions.room}"
        const val datastorePreferences =
            "androidx.datastore:datastore-preferences:${Versions.datastore}"
    }

    object SupportLibs {
        const val ktxCore = "androidx.core:core-ktx:$ktxCoreVersion"
        const val appCompat = "androidx.appcompat:appcompat:$appCompatVersion"
        const val recyclerView = "androidx.recyclerview:recyclerview:$recyclerViewVersion"
        const val fragment = "androidx.fragment:fragment:$fragmentVersion"
        const val navigationFragment =
            "androidx.navigation:navigation-fragment-ktx:$navigationVersion"
        const val navigationUi = "androidx.navigation:navigation-ui-ktx:$navigationVersion"

        const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel:$lifecycleVersion"
        const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime:$lifecycleVersion"
        const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion"
        const val lifecycleCommon = "androidx.lifecycle:lifecycle-common-java8:$lifecycleVersion"
        const val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion"
        const val lifecycleProcess = "androidx.lifecycle:lifecycle-process:$lifecycleVersion"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:$constraintUi"
    }

    object Libraries {
        const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
        const val retrofitSerlialization =
            "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:$serializationConverterVersion"
        const val loggingInterceptor =
            "com.squareup.okhttp3:logging-interceptor:$loggingInterceptorVersion"
        const val materialDesign = "com.google.android.material:material:$materialDesignVersion"
        const val timber = "com.jakewharton.timber:timber:$timberVersion"
    }

    object Testing {
        const val jUnit = "junit:junit:$jUnitVersion"
        const val jUnitAndroid = "androidx.test.ext:junit:$jUnitAndroidVersion"
        const val mockk = "io.mockk:mockk:$mockkVersion"
        const val coroutinesTest =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion"
        const val espressoCore = "androidx.test.espresso:espresso-core:$espressoVersion"
    }

    object Modules {
        const val domain = ":domain"
        const val commons = ":commons"
        const val data = ":data"
        const val feature = ":feature"
    }
}

object DependencyGroups {
    val commonKotlin = listOf(
        Dependencies.Kotlin.kotlin,
        Dependencies.Kotlin.kotlinStdLib,
        Dependencies.Kotlin.coroutines,
        Dependencies.Kotlin.coroutinesKtx,
    )

    val commonAndroid = listOf(
        Dependencies.Kotlin.coroutinesAndroid,
        Dependencies.SupportLibs.ktxCore,
        Dependencies.SupportLibs.appCompat,
        Dependencies.Di.koin,
        Dependencies.Libraries.timber,
    ) + commonKotlin

    val commonTest = listOf(
        Dependencies.Testing.jUnit,
        Dependencies.Testing.coroutinesTest,
    )
    val commonAndroidTest = listOf(
        Dependencies.Testing.jUnitAndroid,
        Dependencies.Testing.espressoCore,
    )

    val navigation = listOf(
        Dependencies.SupportLibs.navigationFragment,
        Dependencies.SupportLibs.navigationUi,
    )

    val fragments = listOf(
        Dependencies.SupportLibs.fragment,
        Dependencies.SupportLibs.lifecycleRuntimeKtx,
        Dependencies.SupportLibs.lifecycleViewModel,
        Dependencies.SupportLibs.lifecycleRuntime,
        Dependencies.SupportLibs.lifecycleCommon,
        Dependencies.SupportLibs.lifecycleLiveData,
        Dependencies.SupportLibs.lifecycleProcess,
    )

    val network = listOf(
        Dependencies.Libraries.retrofit,
        Dependencies.Libraries.retrofitSerlialization,
        Dependencies.Libraries.loggingInterceptor,
        Dependencies.Kotlin.kotlinxSerialization,
    )

    val ui = listOf(
        Dependencies.Libraries.materialDesign,
        Dependencies.SupportLibs.recyclerView,
        Dependencies.SupportLibs.constraintLayout,
    )

    val diUi = listOf(
        Dependencies.Di.koin,
        Dependencies.Di.koinViewModel,
        Dependencies.Di.koinAndroid,
    )

    val dataBase = listOf(
        Dependencies.Database.roomRuntime,
        Dependencies.Database.roomCompiler,
        Dependencies.Database.roomKtx,
        Dependencies.Database.roomTest,
        Dependencies.Database.datastorePreferences,
    )

    val modules = listOf(
        Dependencies.Modules.domain,
        Dependencies.Modules.data,
        Dependencies.Modules.commons,
        Dependencies.Modules.feature,
    )
}
