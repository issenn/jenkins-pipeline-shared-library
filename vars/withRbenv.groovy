#!/usr/bin/env groovy

import io.issenn.jenkins.utils.utils

def call(version='2.5.1', method=null, Closure body={}) {
    def metarunner = 'rbenv'
    def utils = new utils()

    print "Setting up Ruby version ${version}!"

    def command = "command -v ${metarunner}"

    try {
        sh(returnStdout: true, script: command)
    } catch(Exception ex) {
        installRbenv(metarunner)
        installRbenv("ruby-build")
        installRbenv("readline")
        installRbenv("rbenv-gemset")
        installRbenv("rbenv-vars")
    }

    if (fileExists("/usr/local/bin/rbenv")) {
        println("found")
    }

    sh "env"

    if (!fileExists("$HOME/.${metarunner}/versions/${version}/")) {
        sh "env"
        /*
        withEnv(["PATH=$HOME/.${metarunner}/shims:$PATH"]) {
            sh "env"
            utils.installVersion(metarunner, version)
        }*/
    }
/*
    withEnv(["PATH=$HOME/.${metarunner}/shims:$HOME/.${metarunner}/bin/:$PATH", "NODENV_SHELL=sh"]) {
        sh "${metarunner} rehash && ${metarunner} local ${version}"
        cl()
    }

    if (method == 'clean') {
        print "Removing Ruby ${version}!!!"
        withEnv(["PATH=$HOME/.${metarunner}/bin/:$PATH"]) {
            utils.deleteVersion(metarunner, version)
        }
    }*/
}

def installRbenv(metarunner) {
    println("Installing ${metarunner}")
    new utils().installMetarunnerOnMac(metarunner)
}

