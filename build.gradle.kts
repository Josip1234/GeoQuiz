// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.4" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
}
val sourceCompatibility by extra("1.8")
val sourceCompatibility1 by extra(sourceCompatibility)
val targetCompatibility by extra(1.8)
