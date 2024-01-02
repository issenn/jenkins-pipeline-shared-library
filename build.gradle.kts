plugins {
    groovy
    `maven-publish`
    distribution
}

group = "com.issenn.jenkins"
// version = "1.0-SNAPSHOT"
version = "1.0.1"

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
            setUrl("http://192.168.1.58:8081/repository/maven-releases/")
            credentials {
                username = project.findProperty("nexusUsername") as String? ?: System.getenv("NEXUS_USERNAME")
                password = project.findProperty("nexusPassword") as String? ?: System.getenv("NEXUS_PASSWORD")
            }
            isAllowInsecureProtocol = true
        }
    }
}
