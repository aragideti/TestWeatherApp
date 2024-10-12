plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.dagger.hilt.android")
    id ("kotlin-parcelize")
    kotlin("kapt")
}

android {
    namespace = "com.example.testweather"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.testweather"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "BASE_URL", "\"https://api.openweathermap.org/data/2.5/\"")
        buildConfigField("String", "WEATHER_API_KEY", "\"e97c67d4c28ed3556315e4f0026676cb\"")
//        buildConfigField("String", "WEATHER_API_KEY", "\"701c667413994fb16487d2fbf4b9ed31\"")
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

    buildFeatures {
        buildConfig = true
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    implementation (libs.androidx.lifecycle.viewmodel.ktx)
    implementation (libs.androidx.lifecycle.livedata.ktx)
    implementation (libs.androidx.activity.ktx)
    implementation("com.google.dagger:hilt-android:2.51.1")
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")
    implementation ("com.google.android.libraries.places:places:4.0.0")
    implementation ("androidx.fragment:fragment-ktx:1.5.4")
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    kapt ("com.github.bumptech.glide:compiler:4.12.0")

}