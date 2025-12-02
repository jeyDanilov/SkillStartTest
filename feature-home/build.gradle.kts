plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("org.jetbrains.kotlin.kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.feature_home"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures{
        dataBinding = true
    }
}

dependencies {

    implementation(project(":domain"))
    implementation(project(":data"))

    //hilt
    implementation("com.google.dagger:hilt-android:2.51.1")
    kapt("com.google.dagger:hilt-compiler:2.51.1")

    //by viewModels
    implementation("androidx.fragment:fragment-ktx:1.6.1")

    //Coroutines
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")

    //adapter delegate
    implementation("com.hannesdorfmann:adapterdelegates4-kotlin-dsl-viewbinding:4.3.0")

    //Glide
    implementation("com.github.bumptech.glide:glide:4.16.0")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
