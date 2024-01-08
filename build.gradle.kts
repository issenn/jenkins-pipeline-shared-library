plugins {
    groovy
    distribution
    `maven-publish`
}

group = "com.issenn.jenkins"
version = "1.0.0-SNAPSHOT"
// version = "1.0.10"

repositories {
    // https://repo.maven.apache.org/maven2/
    mavenCentral()
    // https://maven.google.com/
    // google()
    maven {
        name = "Jenkins Repository"
        url = uri("https://repo.jenkins-ci.org/public/")
    }
    // maven {
    //     name = "Another Maven Central Repository"
    //     url = uri("https://repo1.maven.org/maven2/")
    // }
}

dependencies {
    implementation("org.codehaus.groovy:groovy-all:2.4.21")
    // implementation("org.jenkins-ci.main:jenkins-core:2.277.4")
    implementation(group = "org.jenkins-ci.main", name = "jenkins-core", version = "2.277.4", ext = "jar")
    // implementation(
    //     group = "org.jenkins-ci", name = "symbol-annotation", version = "1.24", ext = "jar"
    // ) {
    //     artifact {
    //         name = "symbol-annotation"
    //         type = "jar"
    //     }
    // }
    implementation(
        group = "org.jenkins-ci.plugins.workflow", name = "workflow-cps", version = "3826.v3b_5707fe44da_", ext = "jar"
    ) {
        artifact {
            name = "workflow-cps"
            type = "jar"
        }
    }
    implementation(
        group = "io.jenkins.plugins", name = "pipeline-groovy-lib", version = "689.veec561a_dee13", ext = "jar"
    ) {
        artifact {
            name = "pipeline-groovy-lib"
            type = "jar"
        }
    }
    testImplementation(platform("org.junit:junit-bom:5.10.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("com.lesfurets:jenkins-pipeline-unit:1.19")
}

sourceSets {
   main {
       java {
           setSrcDirs(emptyList<String>())
       }
       // groovy.srcDirs("src", "vars")
       groovy {
           setSrcDirs(listOf("src", "vars"))
       }
       // resources.srcDir("resources")
       resources {
           setSrcDirs(listOf("resources"))
       }
   }
   test {
       java {
           setSrcDirs(emptyList<String>())
       }
       // groovy.srcDirs("test/groovy")
       groovy {
           setSrcDirs(listOf("test/groovy", "test/vars"))
       }
       // resources.srcDir("test/resources")
       resources {
           setSrcDirs(listOf("test/resources"))
       }
   }
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

distributions {
    main {
        contents {
            from(".")
            into("/")
            setIncludes(listOf("src/**", "vars/**"))
            setExcludes(listOf("pipeline-syntax.gdsl", "**/*.bak"))
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("Distribution") {
            artifact(tasks.distZip.get())
        }
    }

    repositories {
        maven {
            // setUrl(project.findProperty("mavenRepoURL") as String? ?: System.getenv("MAVEN_REPO_URL"))
            val repoURL = project.findProperty("mavenRepoURL") as String? ?: System.getenv("MAVEN_REPO_URL") ?: ""
            val releasesRepoUrl = repoURL + "maven-releases"
            val snapshotsRepoUrl = repoURL + "maven-snapshots"
            url = uri(if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl)
            credentials(PasswordCredentials::class) {
                username = project.findProperty("mavenUsername") as String? ?: System.getenv("MAVEN_USERNAME")
                password = project.findProperty("mavenPassword") as String? ?: System.getenv("MAVEN_PASSWORD")
            }
            // credentials(HttpHeaderCredentials::class) {
            //     name = "Authorization"
            //     value = "Bearer 6466db12-4f0d-3fb2-a3a8-54735e6d683b"
            // }
            // authentication {
            //     create<HttpHeaderAuthentication>("header")
            // }
            isAllowInsecureProtocol = true
        }
    }
}
