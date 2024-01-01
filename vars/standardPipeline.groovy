#!groovy

def call(Map map) {
    pipeline {
        agent {
            label 'master'
        }

        stages {
            stage('Hello') {
                steps {
                    echo 'Hello World'
                    maintainer('Knight')
                }
            }
        }
    }
}
