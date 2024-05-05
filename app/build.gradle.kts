plugins {
    java
    application
    checkstyle
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

application {
    mainClass = "hexlet.code.App"
}

checkstyle {
    toolVersion = "10.12.4"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("junit", "junit", "4.12")
    checkstyle("com.puppycrawl.tools", "checkstyle","10.12.4")
    implementation ("info.picocli", "picocli", "4.7.5")
    implementation("com.fasterxml.jackson.core", "jackson-databind", "2.17.0")
    implementation("com.google.guava", "guava", "33.2.0-jre")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}