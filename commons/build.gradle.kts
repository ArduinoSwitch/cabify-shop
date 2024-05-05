plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.parcelize)
    id(Plugins.safeArgs)
}

android {
    compileSdk = Config.compileSkd

    defaultConfig {
        minSdk = Config.minSkd
        targetSdk = Config.targetSkd

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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

    viewBinding {
        enable = true
    }
    namespace = "com.cabify.commons"
}

dependencies {
    implementations(DependencyGroups.diUi)
    implementations(DependencyGroups.commonAndroid)
    implementations(DependencyGroups.commonKotlin)
    implementations(DependencyGroups.ui)
    implementations(DependencyGroups.navigation)
    implementation(Dependencies.Database.datastorePreferences)
    implementations(DependencyGroups.commonTest)
    implementations(DependencyGroups.commonAndroidTest)
}