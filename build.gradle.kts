plugins {
    id("java")
}

group = "org.aren_rend"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.15.2")
}

tasks.test {
    useJUnitPlatform()
}