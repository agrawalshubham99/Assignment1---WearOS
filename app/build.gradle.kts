plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.assignment3.shubhamagrawalassignment1"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.assignment3.shubhamagrawalassignment1"
        minSdk = 30
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        buildFeatures {
            viewBinding = true
        }
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
}

dependencies {

    implementation(libs.play.services.wearable)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
}