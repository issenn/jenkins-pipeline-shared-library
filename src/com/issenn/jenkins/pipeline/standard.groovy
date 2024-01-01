package com.issenn.jenkins.pipeline

def call(body) {
    pipeline {
        agent {
            label 'master'
        }

        stages {
            stage('Hello') {
                steps {
                    echo 'Hello World'
                }
            }
        }
    }
}
