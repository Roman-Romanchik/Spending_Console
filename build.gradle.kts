plugins {
    id("java")
    id("application")
    id("org.openjfx.javafxplugin").version("0.1.0")
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
application {
    mainClass = "org.aren_rend.Main"
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

javafx {
    version = "21.0.9"
    modules("javafx.graphics", "javafx.fxml", "javafx.controls")
}