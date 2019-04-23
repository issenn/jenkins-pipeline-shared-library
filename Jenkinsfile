#!/usr/bin/env groovy

import org.jenkinsci.plugins.workflow.libs.Library


@Library('github.com/Taki-Kun/jenkins-pipeline-shared-groovy-library@master') _
/*
library(
    identifier: 'github.com/Taki-Kun/testJenkinsSharedLibraries@master',
    // changelog: false,
    retriever: modernSCM(
        [
            $class: 'GitSCMSource',
            credentialsId: 'ssh-git-credentials-id',
            remote: 'git@github.com:Taki-Kun/testJenkinsSharedLibraries.git'
        ]
    )
)
*/

pipeline {
    agent {
        node {
            label 'mac-mini3'
            customWorkspace "workspace/${JOB_NAME.replace('%2F', '/')}"
        }
    }

    options {
        ansiColor('xterm')
    }

    environment {
        LANG = "C.UTF-8"
        LC_ALL = "en_US.UTF-8"
        LANGUAGE = "en_US.UTF-8"
        // HOME = "/Users/mac"
        PATH = "/usr/local/bin:/usr/bin:/bin:/usr/local/sbin:/usr/sbin:/sbin"
    }

    stages {
        stage('Check Ruby Version') {
            steps {
                // sh "env"
                // sh "ls ${JENKINS_HOME}/"
                withRbenv("2.5.2", "keep") { cl ->
                    sh "rbenv version"
                    sh "ruby --version"
                }
            }
        }
    }
}

/*
def dummy
// binaryNode(label: 'mac-book-pro-1') {
binaryNode(argone: 'one') {
    ws ("workspace/${env.JOB_NAME}/pipelines".replace('%2F', '_')) {
        git 'https://github.com/fabric8io/fabric8-pipeline-library.git'
        // checkoutGeneralSCM(browser: 'github', url: 'https://github.com/Taki-Kun/testJenkinsSharedLibraries.git', name: 'master')
    }
}
*/
