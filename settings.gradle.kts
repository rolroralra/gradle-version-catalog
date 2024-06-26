rootProject.name = "project"

dependencyResolutionManagement {
    repositories {
        maven {
            url = uri("https://maven.pkg.github.com/rolroralra/maven-repo")
            credentials {
                username = extra.findProperty("repo.user") ?: System.getenv("REPO_USERNAME")
                password = extra.findProperty("repo.token") ?: System.getenv("REPO_TOKEN")
            }
        }
        mavenCentral()
    }

//    versionCatalogs {
//        create("libs") {
//            from("com.rolroralra.spring.gradle:catalog:1.0.0")
//        }
//    }
}

fun ExtraPropertiesExtension.findProperty(name: String): String? {
    return if (has(name)) {
        get(name) as String?
    } else {
        null
    }
}
