import java.util.*

plugins {
    id("java-library")
    id("maven-publish")
    id("com.jfrog.bintray")
    id("com.github.hierynomus.license")
    id("com.github.sgtsilvio.gradle.utf8")
    id("com.github.sgtsilvio.gradle.metadata")
    id("com.github.sgtsilvio.gradle.javadoc-links")
}


/* ******************** metadata ******************** */

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
        developer {
            id = "cschaebe"
            name = "Christoph Schaebel"
            email = "christoph.schaebel@hivemq.com"
        }
        developer {
            id = "lbrandl"
            name = "Lukas Brandl"
            email = "lukas.brandl@hivemq.com"
        }
        developer {
            id = "flimpoeck"
            name = "Florian Limpoeck"
            email = "florian.limpoeck@hivemq.com"
        }
        developer {
            id = "sauroter"
            name = "Georg Held"
            email = "georg.held@hivemq.com"
        }
        developer {
            id = "SgtSilvio"
            name = "Silvio Giebl"
            email = "silvio.giebl@hivemq.com"
        }
    }
    github {
        org = "hivemq"
        repo = "hivemq-extension-sdk"
        issues()
    }
}


/* ******************** dependencies ******************** */

repositories {
    mavenCentral()
}

dependencies {
    api("io.dropwizard.metrics:metrics-core:${property("metrics.version")}")
    api("org.slf4j:slf4j-api:${property("slf4j.version")}")
}


/* ******************** java ******************** */

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11

    withJavadocJar()
    withSourcesJar()
}

tasks.withType<Jar>().configureEach {
    manifest.attributes(
            "Implementation-Title" to project.name,
            "Implementation-Vendor" to metadata.organization.name,
            "Implementation-Version" to project.version)
}

tasks.javadoc {
    title = "${metadata.readableName} ${project.version} API"

    doLast {
        javaexec {
            main = "-jar"
            args("${projectDir}/gradle/tools/javadoc-cleaner-1.0.jar")
        }
    }
}


/* ******************** publishing ******************** */

publishing.publications.register<MavenPublication>("extensionSdk") {
    from(components["java"])
}

bintray {
    user = "${project.findProperty("bintrayUser") ?: System.getenv("BINTRAY_USER")}"
    key = "${project.findProperty("bintrayApiKey") ?: System.getenv("BINTRAY_API_KEY")}"
    dryRun = false
    publish = false
    override = false
    pkg.apply {
        userOrg = "hivemq"
        repo = "HiveMQ"
        name = "hivemq-extension-sdk"
        desc = project.description
        websiteUrl = metadata.url
        issueTrackerUrl = metadata.issueManagement.url
        vcsUrl = metadata.scm.url
        setLicenses(metadata.license.shortName)
        setLabels("hivemq", "extension", "sdk", "mqtt", "mqtt5")
        publicDownloadNumbers = false
        version.apply {
            released = Date().toString()
            gpg.apply {
                sign = true
            }
            mavenCentralSync.apply {
                sync = false
                user = "${project.findProperty("mavenCentralUser") ?: System.getenv("MAVEN_CENTRAL_USER")}"
                password = "${project.findProperty("mavenCentralKey") ?: System.getenv("MAVEN_CENTRAL_KEY")}"
                close = "0"
            }
        }
    }
}
afterEvaluate {
    bintray.setPublications(*publishing.publications.withType<MavenPublication>().names.toTypedArray())
}


/* ******************** checks ******************** */

license {
    header = file("${projectDir}/HEADER")
    mapping("java", "SLASHSTAR_STYLE")
}
