// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.3" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
}

buildscript {
    repositories {
        maven("https://maven.aliyun.com/repository/public/")
        maven("https://maven.aliyun.com/repository/central")
        mavenLocal()
        mavenCentral()
    }
}