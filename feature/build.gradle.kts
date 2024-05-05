plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.safeArgs)
    id(Plugins.parcelize)
}

android {
    compileSdk = Config.compileSkd

    defaultConfig {
        minSdk = Config.minSkd
        targetSdk = Config.targetSkd

        testInstrumentationRunner = Config.testInstrumentationRunner
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile(Config.defaultProguardFile),
                file(Config.proguardRules)
            )
        }
    }

    viewBinding {
        enable = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = Config.jvtTarget
    }
    namespace = "com.cabify.feature"
}

dependencies {
    modules(listOf(Dependencies.Modules.commons, Dependencies.Modules.domain))
    implementations(DependencyGroups.commonKotlin)
    implementations(DependencyGroups.navigation)
    implementations(DependencyGroups.commonAndroid)
    implementations(DependencyGroups.diUi)
    implementations(DependencyGroups.commonTest)
    implementations(DependencyGroups.commonAndroidTest)
}
