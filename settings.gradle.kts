pluginManagement {
    if (file("../hivemq/plugins").exists()) {
        // includeBuild("../hivemq/plugins")
    }
    if (file("../hivemq-edge-composite/plugins").exists()) {
        includeBuild("../hivemq-edge-composite/plugins")
    }


}

rootProject.name = "hivemq-extension-sdk"
