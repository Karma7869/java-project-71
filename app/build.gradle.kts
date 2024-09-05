import org.gradle.testing.jacoco.plugins.JacocoPluginExtension

plugins {
    id("java")
    id ("application")
    id ("checkstyle")
    id("jacoco")
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    mainClass = ("hexlet.code.App")
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation ("info.picocli:picocli:4.7.6")
    implementation ("com.fasterxml.jackson.core:jackson-databind:2.13.1")
}

tasks.withType<Checkstyle> {
    configFile = file("config/checkstyle/checkstyle.xml")
}


tasks.test {
    useJUnitPlatform()
    finalizedBy("jacocoTestReport")
}

the<JacocoPluginExtension>().apply {
    toolVersion = "0.8.12"
}

tasks.named<JacocoReport>("jacocoTestReport") {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}
