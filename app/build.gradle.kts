group = "hexlet.code"
version = "1.0-SNAPSHOT"

plugins {
    application
    checkstyle
    jacoco
}

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
    implementation("com.fasterxml.jackson.dataformat", "jackson-dataformat-yaml", "2.17.1")
    implementation("com.google.guava", "guava", "33.2.0-jre")
}

tasks.test {
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required = true
    }
}

jacoco {
    toolVersion = "0.8.12"
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}