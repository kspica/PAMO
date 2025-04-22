plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.bmicalculatorplus"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.bmicalculatorplus"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.core.splashscreen)
    implementation(libs.transition)
    implementation(libs.gson)
    testImplementation(libs.junit)
    testImplementation(libs.core.testing)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.junit.jupiter)
    testImplementation(libs.junit.jupiter.api)
    testImplementation(libs.ext.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    debugImplementation("androidx.fragment:fragment-testing-manifest:1.6.2")
    androidTestImplementation("androidx.fragment:fragment-testing:1.6.2")
    testRuntimeOnly(libs.jupiter.junit.jupiter.engine)

}

tasks.withType<Test> {
    useJUnit()
}
