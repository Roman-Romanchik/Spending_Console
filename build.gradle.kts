plugins {
    id("java")
    id("application")
    id("org.openjfx.javafxplugin").version("0.1.0")
}

group = "org.aren_rend"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
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
tasks.register<Jar>("fatJar") {
    archiveBaseName = "myapp-all"
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    from(sourceSets.main.get().output)
    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get()
            .filter { it.name.endsWith("jar") }
            .map { zipTree(it) }
    })

    manifest {
        attributes["Main-Class"] = "org.aren_rend.Main"
    }
}