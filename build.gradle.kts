buildscript {
    if (gradle.includedBuilds.any { it.name == "plugins" }) {
        plugins {
            id("com.hivemq.version-updater")
        }
    }
}

plugins {
    `java-library`
    `maven-publish`
    signing
    alias(libs.plugins.mavenCentralPublishing)
    alias(libs.plugins.defaults)
    alias(libs.plugins.metadata)
    alias(libs.plugins.javadocLinks)
    alias(libs.plugins.license)
}

plugins.withId("com.hivemq.version-updater") {
    project.ext["versionUpdaterFiles"] = arrayOf("README.adoc")
}

group = "com.hivemq"
description = "SDK for the development of HiveMQ extensions"

metadata {
    readableName = "HiveMQ Extension SDK"
    organization {
        name = "HiveMQ GmbH"
        url = "https://www.hivemq.com/"
    }
    license {
        apache2()
    }
    developers {
        register("cschaebe") {
            fullName = "Christoph Schaebel"
            email = "christoph.schaebel@hivemq.com"
        }
        register("lbrandl") {
            fullName = "Lukas Brandl"
            email = "lukas.brandl@hivemq.com"
        }
        register("flimpoeck") {
            fullName = "Florian Limpoeck"
            email = "florian.limpoeck@hivemq.com"
        }
        register("sauroter") {
            fullName = "Georg Held"
            email = "georg.held@hivemq.com"
        }
        register("SgtSilvio") {
            fullName = "Silvio Giebl"
            email = "silvio.giebl@hivemq.com"
        }
    }
    github {
        issues()
    }
}

repositories {
    mavenCentral()
}

dependencies {
    api(platform(libs.dropwizard.metrics.bom))
    api(libs.dropwizard.metrics.core)
    api(libs.slf4j.api)
}

/* ******************** java ******************** */

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(11)
    }
    withJavadocJar()
    withSourcesJar()
}

tasks.withType<Jar>().configureEach {
    manifest.attributes(
        "Implementation-Title" to project.name,
        "Implementation-Vendor" to metadata.organization.provider.flatMap { it.name },
        "Implementation-Version" to provider { project.version.toString() },
    )
}

tasks.javadoc {
    title = "${metadata.readableName.get()} ${project.version} API"

    val javadocCleanerResult = providers.javaexec {
        classpath("gradle/tools/javadoc-cleaner-1.0.jar")
    }.result
    doLast {
        javadocCleanerResult.get()
    }
}

/* ******************** publishing ******************** */

publishing {
    publications {
        register<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}

signing {
    isRequired = !"true".equals(project.findProperty("signingDisabled") as String?, true)
    val signingKey: String? by project
    val signingPassword: String? by project
    useInMemoryPgpKeys(signingKey, signingPassword)
    sign(publishing.publications["maven"])
}

/* ******************** checks ******************** */

license {
    header = file("HEADER")
    mapping("java", "SLASHSTAR_STYLE")
}
