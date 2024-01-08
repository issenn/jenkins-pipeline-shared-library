package com.issenn.jenkins.pipeline

def call(Closure body={}) {
    pipeline {
        agent {
            label 'master'
        }

        stages {
            stage('Hello') {
                steps {
                    echo 'Hello, I am Issenn.'
                }
            }
        }
    }
}
