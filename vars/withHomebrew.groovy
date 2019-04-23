#!/usr/bin/env groovy

import io.issenn.jenkins.utils.utils

def call(Map parameters = [:], String method=null, Closure body) {
    String metarunner = 'brew'

    println("Setting up Homebrew!")

    def command = "command -v ${metarunner}"
    // def out = new StringBuilder(), err = new StringBuilder()
    // def out = new StringBuffer()
    // def err = new StringBuffer()
    // Process process = command.execute()
    // process.consumeProcessOutput(out, err)
    // process.waitFor()
    // process.waitForOrKill(1000)
    // println process.text
    // if( out.size() > 0 ) println out.toString()
    // if( err.size() > 0 ) println err.toString()

    try {
        sh(returnStdout: true, script: command)
    } catch(Exception ex) {
        println("Please install Homebrew!")
        // installHomebrew(metarunner)
    }
}

def installHomebrew(String metarunner) {
    print "Installing ${metarunner}"
    new utils().installHomebrew()
}
