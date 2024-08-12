plugins {
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.android.hilt)
}

android {
    namespace = "com.prasad.network"
    compileSdk = Integer.parseInt(libs.versions.compileSdkVersion.get())

    defaultConfig {
        minSdk =  Integer.parseInt(libs.versions.minSdkVersion.get())

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
        buildConfigField("String", "BASE_URL", "\"https://api.sampleapis.com/cartoons/\"")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures.buildConfig = true
}

dependencies {

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    // Retrofit
    implementation(libs.retofit)
    implementation(libs.gson.converter)
    implementation(libs.logging.interceptor)

    // Gson
    implementation(libs.gson)

    implementation(project(":domain"))
}