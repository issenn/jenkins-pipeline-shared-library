#!groovy

import com.issenn.jenkins.security.PAM

def call(Map args=[:], Closure body={}) {
    def config = [
        wrapper: 'default',
        labels: [],
        artifactRepoBaseUrl: 'http://192.168.1.58:8081/repository/',
        artifactRepoName: 'maven-releases',
        artifactGroup: "com.issenn.ansible",
        artifactName: "ansible-playbook-sample",
        artifactVersion: "1.0.1",
        artifactExt: "zip",
        ip: "192.168.1.58,",
        ansibleInventory: "192.168.1.58,",
        ansiblePassword: "issennknight"
    ] << args

    Closure closure = body.clone() as Closure
    closure.resolveStrategy = Closure.DELEGATE_FIRST
    config.with closure

    artifactRepoBaseUrl = config.get("artifactRepoBaseUrl")
    artifactVersion = config.get("artifactVersion")
    // ansiblePassword = config.get("ansiblePassword")
    ansiblePassword = PAM.getPassword()

    pipeline {
        agent {
            node {
                label 'ansible'
                // customWorkspace "workspace/${JOB_NAME.replace('%2F', '/')}"
            }
        }

        // options {
        //     // ansiColor('xterm')
        // }

        environment {
            LANG = "C.UTF-8"
            LC_ALL = "C.UTF-8"
            LANGUAGE = "C.UTF-8"
            LC_CTYPE = "C.UTF-8"
            ANSIBLE_HOST_KEY_CHECKING = false
            ANSIBLE_SSH_HOST_KEY_CHECKING = false
            // PATH = "/usr/local/bin:/usr/bin:/bin:/usr/local/sbin:/usr/sbin:/sbin"
        }

        // parameters {
        //     string(name: 'knight4', defaultValue: 'Hello', description: 'How should I greet the world?')
        // }

        stages {
            stage('Check request parameters') {
                steps {
                    echo("Todo")
                    echo("${params.knight10}")
                    sh "env"
                }
            }

            stage('Check Ansible Version') {
                steps {
                    sh 'ansible --version'
                }
            }

            stage('CheckOut') {
                steps {
                    maintainer('Knight')
                    sh("curl -fsSL ${artifactRepoBaseUrl}/maven-releases/com/issenn/ansible/ansible-playbook-sample/${artifactVersion}/ansible-playbook-sample-${artifactVersion}.zip -o ansible.zip")
                    unzip(
                        zipFile: 'ansible.zip',
                        dir: 'ansible'
                    )
                    dir('ansible') {
                        ansiblePlaybook(
                            colorized: true,
                            // installation: 'ansible2.5.3',
                            inventory: "192.168.1.58,",
                            playbook: 'test.yml',
                            extras: "--extra-vars 'ansible_user=issenn' --extra-vars 'ansible_password=${ansiblePassword}'"
                        )
                    }
                }
            }
        }

        post {
            always {
                archiveArtifacts(
                    artifacts: 'ansible.zip',
                    onlyIfSuccessful: true
                )
            }
        }
    }
}
