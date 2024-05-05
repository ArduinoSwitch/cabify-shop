plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.serialization)
}

android {
    compileSdk = Config.compileSkd
    namespace = "com.cabify.domain"

    kotlin {
        jvmToolchain(17)
    }
}

dependencies {
    modules(listOf(Dependencies.Modules.commons))
    implementations(DependencyGroups.commonKotlin)
    implementation(Dependencies.Di.koin)
    implementation(Dependencies.Kotlin.kotlinxSerialization)
    implementations(DependencyGroups.commonTest)
    implementations(DependencyGroups.commonAndroidTest)
}