
plugins {
    //  alias(libs.plugins.androidLibrary)
    //   alias(libs.plugins.jetbrainsKotlinAndroid)
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")

}


group = "com.github.tinyCodes1"
version = "1.5"



android {
    namespace = "com.tinycode.js"
    compileSdk = 34

    defaultConfig {
        /*   aarMetadata {
               minCompileSdk = 26
           }*/
        minSdk = 26
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
        vectorDrawables { useSupportLibrary = true }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}



dependencies {

    implementation("androidx.compose.ui:ui-graphics:1.6.7")
    implementation("androidx.documentfile:documentfile:1.0.1")
    implementation("androidx.compose.ui:ui:1.6.7")
    debugImplementation("androidx.compose.ui:ui-tooling:1.6.7")
    implementation("androidx.compose.ui:ui-tooling-preview:1.6.7")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.9.0")

    implementation("androidx.activity:activity-compose:1.9.0")
    implementation("androidx.compose:compose-bom:2024.05.00")
    androidTestImplementation("androidx.compose:compose-bom:2024.05.00")
    implementation("androidx.compose.material3:material3:1.2.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4-desktop:1.6.7")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.6.7")

    implementation("androidx.core:core-ktx:1.13.1")
    testImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.6.7")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")


}



afterEvaluate {
    publishing {
        publications {
            // Creates a Maven publication called "release".
            create<MavenPublication>("release") {
                from(components["release"])
                groupId = "com.github.tinyCodes1"
                artifactId = "addjs"
                version = "1.5"

            }
        }
    }
}

