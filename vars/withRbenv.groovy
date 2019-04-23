#!/usr/bin/env groovy

import io.issenn.jenkins.utils.utils

def call(version='2.5.1', method=null, cl) {
    def metarunner = 'rbenv'
    def utils = new utils()

    print "Setting up Ruby version ${version}!"

    def command = "command -v ${metarunner} 1>/dev/null 2>&1"

    if (!sh(returnStdout: true, script: command)) {
        def out = sh(returnStdout: true, script: command)
        println(out)
    }

    /*
    try {
        sh(returnStdout: true, script: command)
    } catch(Exception ex) {
        installRbenv(metarunner)
        installRbenv("ruby-build")
        installRbenv("readline")
        installRbenv("rbenv-gemset")
        installRbenv("rbenv-vars")
    }

    if (!fileExists("$HOME/.${metarunner}/versions/${version}/")) {
        withEnv(["PATH=$HOME/.${metarunner}/bin/:$PATH"]) {
            utils.installVersion(metarunner, version)
        }
    }

    withEnv(["PATH=$HOME/.${metarunner}/shims:$HOME/.${metarunner}/bin/:$PATH", "NODENV_SHELL=sh"]) {
        sh "${metarunner} rehash && ${metarunner} local ${version}"
        cl()
    }

    if (method == 'clean') {
        print "Removing Ruby ${version}!!!"
        withEnv(["PATH=$HOME/.${metarunner}/bin/:$PATH"]) {
            utils.deleteVersion(metarunner, version)
        }
    }
     */
}

def installRbenv(metarunner) {
    println("Installing ${metarunner}")
    new utils().installMetarunnerOnMac(metarunner)
}

