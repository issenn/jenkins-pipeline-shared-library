#!groovy

def call(args=[:]) {
    // args = [
    //     wrapper: 'default',
    //     labels: []
    // ] << args

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
