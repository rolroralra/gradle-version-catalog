val currentVersion: String by project

plugins {
    java
    `maven-publish`
    `version-catalog`
}

group = "com.rolroralra.spring.gradle"
version = currentVersion

tasks.register<Copy>("copyLibsVersions") {
    description = "Copy libs.versions.toml to resources"
    group = "build"
    from("gradle/libs.versions.toml")
    into("${layout.buildDirectory.get()}/resources/main/META-INF")
}

catalog {
    versionCatalog {
        from(files("gradle/libs.versions.toml"))
        library("spring-boot-dependencies", project.group as String, "spring-boot-dependencies").version(project.version as String)
    }
}

publishing {
    publications {
        create<MavenPublication>("versionCatalog") {
            groupId = project.group as String
            artifactId = "catalog"
            version = project.version as String
            from(components["versionCatalog"])
        }
    }

    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/rolroralra/maven-repo")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_REPO_USERNAME")
                password = project.findProperty("gpr.token") as String? ?: System.getenv("GITHUB_REPO_KEY")
            }
        }
    }
}
