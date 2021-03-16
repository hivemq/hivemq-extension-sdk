import java.util.*

plugins {
    id("java-library")
    id("maven-publish")
    id("com.jfrog.bintray")
    id("com.github.hierynomus.license")
    id("com.github.sgtsilvio.gradle.utf8")
    id("com.github.sgtsilvio.gradle.metadata")
    id("com.github.sgtsilvio.gradle.javadoc-links")
    signing
}


/* ******************** metadata ******************** */

group = "com.hivemq"
description = "SDK for the development of HiveMQ extensions"

metadata {
    readableName.set("HiveMQ Extension SDK")
    organization {
        name.set("HiveMQ GmbH")
        url.set("https://www.hivemq.com/")
    }
    license {
        apache2()
    }
    developers {
        developer {
            id.set("cschaebe")
            name.set("Christoph Schaebel")
            email.set("christoph.schaebel@hivemq.com")
        }
        developer {
            id.set("lbrandl")
            name.set("Lukas Brandl")
            email.set("lukas.brandl@hivemq.com")
        }
        developer {
            id.set("flimpoeck")
            name.set("Florian Limpoeck")
            email.set("florian.limpoeck@hivemq.com")
        }
        developer {
            id.set("sauroter")
            name.set("Georg Held")
            email.set("georg.held@hivemq.com")
        }
        developer {
            id.set("SgtSilvio")
            name.set("Silvio Giebl")
            email.set("silvio.giebl@hivemq.com")
        }
    }
    github {
        org.set("hivemq")
        repo.set("hivemq-extension-sdk")
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
            "Implementation-Vendor" to metadata.organization!!.name.get(),
            "Implementation-Version" to project.version)
}

tasks.javadoc {
    title = "${metadata.readableName.get()} ${project.version} API"

    doLast {
        javaexec {
            main = "-jar"
            args("$projectDir/gradle/tools/javadoc-cleaner-1.0.jar")
        }
    }

    doLast { // javadoc search fix for jdk 11 https://bugs.openjdk.java.net/browse/JDK-8215291
        copy {
            from("$buildDir/docs/javadoc/search.js")
            into("$buildDir/tmp/javadoc/")
            filter { line -> line.replace("if (ui.item.p == item.l) {", "if (item.m && ui.item.p == item.l) {") }
        }
        delete("$buildDir/docs/javadoc/search.js")
        copy {
            from("$buildDir/tmp/javadoc/search.js")
            into("$buildDir/docs/javadoc/")
        }
    }
}


/* ******************** publishing ******************** */

publishing {
    publications {
        create<MavenPublication>("extensionSdk") {
            pom {
                name.set(project.name)
                description.set(project.description)
                url.set(project.metadata.organization!!.url)
                licenses {
                    project.metadata.license
                }
                developers {
                    project.metadata.developers
                }
                scm {
                    connection.set("scm:git:git://github.com/hivemq/${project.metadata.github!!.repo}.git")
                    developerConnection.set("scm:git:ssh://github.com/hivemq/${project.metadata.github!!.repo}.git")
                    url.set("http://github.com/hivemq/${project.metadata.github!!.repo}/")
                }
            }
        }
    }
    repositories {
        maven {
            url = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2")
            credentials {
                username = "${project.findProperty("sonatypeUser") ?: System.getenv("SONATYPE_USER")}"
                password = "${project.findProperty("sonatypePassword") ?: System.getenv("SONATYPE_PASSWORD")}"
            }
        }
    }
}

signing {
    val signingKey = "${project.findProperty("signingKey") ?: System.getenv("SIGNING_KEY")}";
    val signingPassword = "${project.findProperty("signingPassword") ?: System.getenv("SIGNING_PASSWORD")}";
    useInMemoryPgpKeys(signingKey, signingPassword)
    sign(publishing.publications["extensionSdk"])
}

/* ******************** checks ******************** */

license {
    header = file("$projectDir/HEADER")
    mapping("java", "SLASHSTAR_STYLE")
}
