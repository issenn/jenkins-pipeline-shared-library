#!/usr/bin/env groovy

import io.issenn.jenkins.utils.utils


def call(String version='2.5.1', String method=null, Closure cl={}) {
// def call(Map parameters = [:], Closure cl={}) {

    // String version = parameters.get('version', '2.5.1')
    // String method = parameters.get('method', 'keep')

    String metarunner = 'rbenv'

    if (fileExists(".ruby-version")) {
        def ruby_version = readFile(file: ".ruby-version", encoding: "utf-8").trim()
        if (ruby_version) version = ruby_version
    }

    def utils = new utils()

    print "Setting up Ruby version ${version}!"

    def command = "command -v ${metarunner}"

    try {
        sh(returnStdout: true, script: command)
    } catch(Exception ex) {
        installRbenv(metarunner)
    }

    while (!fileExists("$HOME/.${metarunner}/versions/${version}/")) {
        try {
            utils.installVersion(metarunner, version,
                "--disable-install-doc --with-readline-dir=\$(brew --prefix readline)")
        } catch(Exception ex) {
            println(ex)
            sh "rm -rf $HOME/.${metarunner}/versions/system"
        }
    }

    withEnv(["PATH=$HOME/.${metarunner}/shims:$PATH", "RBENV_SHELL=zsh"]) {
        // sh "${metarunner} rehash"
        cl()
    }

    if (method == 'clean') {
        print "Removing Ruby ${version}!!!"
        utils.deleteVersion(metarunner, version)
    }
}

def installRbenv(String metarunner) {
    println("Installing ${metarunner}")
    new utils().installMetarunnerOnHomebrew(metarunner)
}

def purgeAll(String metarunner) {
    print "Removing all versions of ${metarunner}"
    new utils().purgeAllVersions(metarunner)
}
