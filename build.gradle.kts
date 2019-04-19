import com.gradle.scan.plugin.BuildScanPlugin
import com.mkobit.jenkins.pipelines.http.AnonymousAuthentication
import org.gradle.kotlin.dsl.version
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.io.ByteArrayOutputStream

plugins {
  groovy
  java
  kotlin("jvm") version "1.3.21"
  id("com.gradle.build-scan") version "2.2.1"
  id("com.mkobit.jenkins.pipelines.shared-library") version "0.8.0"
  // id("com.github.ben-manes.versions") version "0.20.0"
}

val commitSha: String by lazy {
  ByteArrayOutputStream().use {
    project.exec {
      commandLine("git", "rev-parse", "HEAD")
      standardOutput = it
    }
    it.toString(Charsets.UTF_8.name()).trim()
  }
}

buildScan {
  setTermsOfServiceUrl("https://gradle.com/terms-of-service")
  setTermsOfServiceAgree("yes")
  link("GitHub", "https://github.com/Taki-Kun/jenkins-pipeline-shared-groovy-library.git")
  value("Revision", commitSha)
}

group = "io.issenn.jenkins"
version = "1.0-SNAPSHOT"

tasks {
  wrapper {
    gradleVersion = "5.0"
  }
}

repositories {
  mavenCentral()
  jcenter()
}

dependencies {
  compile("org.codehaus.groovy:groovy-all:2.3.11")
  implementation(kotlin("stdlib-jdk8"))
  // testCompile("junit", "junit", "4.12")
  testImplementation("junit", "junit", "4.12")
  // testImplementation(group: 'junit', name: 'junit', version: '4.12')

  // val spock = "org.spockframework:spock-core:1.2-groovy-2.4"
  // testImplementation(spock)
  // testImplementation("org.assertj:assertj-core:3.11.1")
  // integrationTestImplementation(spock)
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

java {
  sourceCompatibility = JavaVersion.VERSION_1_8
}

jenkinsIntegration {
  baseUrl.set(uri("http://localhost:5050").toURL())
  authentication.set(providers.provider { AnonymousAuthentication })
  downloadDirectory.set(layout.projectDirectory.dir("jenkinsResources"))
}

sharedLibrary {
  // TODO: this will need to be altered when auto-mapping functionality is complete
  coreVersion.set(jenkinsIntegration.downloadDirectory.file("core-version.txt").map { it.asFile.readText().trim() })
  testHarnessVersion.set("2.49")
  // TODO: retrieve downloaded plugin resource
  pluginDependencies {
    workflowCpsGlobalLibraryPluginVersion.set("2.13")
    dependency("io.jenkins.blueocean", "blueocean-web", "1.14.0")
    // dependency("org.jenkins-ci.plugins", "pipeline-build-step", "2.7")
    // dependency("org.6wind.jenkins", "lockable-resources", "2.3")
    // val declarativePluginsVersion = "1.3.3"
    // dependency("org.jenkinsci.plugins", "pipeline-model-api", declarativePluginsVersion)
    // dependency("org.jenkinsci.plugins", "pipeline-model-declarative-agent", "1.1.1")
    // dependency("org.jenkinsci.plugins", "pipeline-model-definition", declarativePluginsVersion)
    // dependency("org.jenkinsci.plugins", "pipeline-model-extensions", declarativePluginsVersion)
  }
}
