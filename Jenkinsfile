#!groovy


// import org.jenkinsci.plugins.workflow.libs.Library
// @Library('github.com/issenn/jenkins-pipeline-shared-library@cfets') _
// import com.issenn.jenkins.pipeline.standard
// ci = new standard()
// ci.call(null)


// library(
//     identifier: 'jenkins-pipeline-shared-library@cfets',
//     changelog: false,
//     retriever: modernSCM(
//         [
//             $class: 'GitSCMSource',
//             remote: 'http://192.168.1.58:13000/issenn/jenkins-pipeline-shared-library.git',
//             branch: 'cfets'
//         ]
//     )
// )
// maintainer()

def ver = '1.0.2'
library(
    identifier: 'jenkins-pipeline-shared-library@cfets',
    retriever: http(
        httpURL: "http://192.168.1.58:8081/repository/maven-releases/com/issenn/jenkins/jenkins-pipeline-shared-library/${ver}/jenkins-pipeline-shared-library-${ver}.zip",
        credentialsId: '',
        preemptiveAuth: false
    )
)
standardPipeline()
