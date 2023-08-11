rootProject.name = "hivemq-extension-sdk"

pluginManagement {
    if (file("../hivemq/plugins").exists()) {
        includeBuild("../hivemq/plugins")
    }
}
