# Gradle Version Catalog
[![Gradle Build & Test](https://github.com/rolroralra/gradle-version-catalog/actions/workflows/gradle-build.yaml/badge.svg)](https://github.com/rolroralra/gradle-version-catalog/actions/workflows/gradle-build.yaml)
[![Gradle Build & Test](https://github.com/rolroralra/gradle-version-catalog/actions/workflows/semantic-release.yaml/badge.svg)](https://github.com/rolroralra/gradle-version-catalog/actions/workflows/semantic-release.yaml)

[Gradle Version Catalog](https://docs.gradle.org/current/userguide/platforms.html)

# 사용방법
## 1. settings.gradle.kts에 아래 코드 추가
```kotlin
dependencyResolutionManagement {
    fun ExtraPropertiesExtension.findProperty(name: String): String? {
        return if (has(name)) {
            get(name) as String?
        } else {
            null
        }
    }

    repositories {
        maven {
            url = uri("https://maven.pkg.github.com/rolroralra/maven-repo")
            credentials {
                username = extra.findProperty("repo.user") ?: System.getenv("REPO_USERNAME")
                password = extra.findProperty("repo.token") ?: System.getenv("REPO_TOKEN")
            }
        }
    }
    versionCatalogs {
        create("libs") {
            from("com.rolroralra.spring.gradle.catalog:1.0.0")
        }
    }
}
```

## 2. build.gradle.kts에 아래 코드 추가
```kotlin
subprojects {
    val backendLibs = rootProject.backendLibs
    // ...
    
    dependencies {
        implementation(platform(backendLibs.socar.spring.boot.dependencies))
        // ...
    }
}
```

# Semantic Release 설정 가이드
[Semantic Release 설정 가이드 - Wiki](https://github.com/rolroralra/gradle-version-catalog/wiki/Semantic-Release-%EC%84%A4%EC%A0%95)
