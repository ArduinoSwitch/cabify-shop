plugins {
    id(Plugins.application)
    id(Plugins.kotlinAndroid)
    id(Plugins.safeArgs)
}

android {
    namespace = "com.cabify.shop"
    compileSdk = Config.compileSkd

    defaultConfig {
        applicationId = Config.applicationId
        minSdk = Config.minSkd
        targetSdk = Config.targetSkd
        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }
}

dependencies {
    modules(DependencyGroups.modules)
    implementations(DependencyGroups.commonKotlin)
    implementations(DependencyGroups.commonAndroid)
    implementations(DependencyGroups.diUi)
    implementations(DependencyGroups.navigation)
    implementations(DependencyGroups.commonTest)
    implementations(DependencyGroups.commonAndroidTest)
}