plugins {
    id("java")
    id ("application")
    id ("checkstyle")
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
}