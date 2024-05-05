import org.gradle.api.Project
import org.gradle.api.plugins.BasePluginExtension
import org.gradle.api.plugins.ExtensionContainer

object Config {
    const val applicationId = "com.cabify.shop"
    const val compileSkd = 34
    const val minSkd = 24
    const val targetSkd = 34
    const val versionCode = 1
    const val versionName = "1.0"
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val defaultProguardFile = "proguard-android-optimize.txt"
    const val proguardRules = "proguard-rules.pro"
    const val jvtTarget = "17"
}
