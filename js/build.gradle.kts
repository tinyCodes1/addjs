plugins {
  //  alias(libs.plugins.androidLibrary)
 //   alias(libs.plugins.jetbrainsKotlinAndroid)
    id("com.android.library")
    id("kotlin-android")
    id("maven-publish")

}


group = "com.github.tinyCodes1"
version = "1.2"


android {
    namespace = "com.tinycode.js"
    compileSdk = 34

    defaultConfig {
        aarMetadata {
            minCompileSdk = 26
        }
        minSdk = 26
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.androidx.documentfile)
    implementation(libs.gson)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
}


afterEvaluate {
    publishing {
        publications {
            // Creates a Maven publication called "release".
            create<MavenPublication>("release") {
                from(components["release"])
                groupId = "com.github.tinyCodes1"
                artifactId = "addjs"
                version = "1.2"
            }
        }
    }
}
