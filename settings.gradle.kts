rootProject.name = "hivemq-extension-sdk"

pluginManagement {
    plugins {
        id("com.github.hierynomus.license") version "${extra["plugin.license.version"]}"
        id("com.jfrog.bintray") version "${extra["plugin.bintray.version"]}"
        id("com.github.sgtsilvio.gradle.utf8") version "${extra["plugin.utf8.version"]}"
        id("com.github.sgtsilvio.gradle.metadata") version "${extra["plugin.metadata.version"]}"
        id("com.github.sgtsilvio.gradle.javadoc-links") version "${extra["plugin.javadoc-links.version"]}"
        id("io.github.gradle-nexus.publish-plugin") version "${extra["plugin.io.github.gradle-nexus.publish-plugin.version"]}"
    }
}
