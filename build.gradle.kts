plugins {
    id("groovy")
}

group = "com.issenn.jenkins"
version = "1.0-SNAPSHOT"

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
    implementation("org.jenkins-ci.main:jenkins-core:2.277.4")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

sourceSets {
   main {
       // don't add vars - it's loaded dynamic!
       // groovy {
       //     srcDirs("${project.rootDir}/src")
       // }
       groovy.srcDirs("src", "vars")
       // resources {
       //     srcDirs("${project.rootDir}/resources")
       // }
       resources.srcDir("resources")
   }
   test {
       // groovy {
       //     srcDirs("${project.rootDir}/test")
       // }
       groovy.srcDir("test")
   }
}

tasks.test {
    useJUnitPlatform()
}
