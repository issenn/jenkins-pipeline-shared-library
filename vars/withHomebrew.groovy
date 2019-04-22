#!/usr/bin/env groovy


def call(method=null, Closure body={}) {
    def metarunner = 'brew'

    print "Setting up Homebrew!"

    def command = "command -v ${metarunner}"
    // def out = new StringBuilder(), err = new StringBuilder()
    // def out = new StringBuffer()
    // def err = new StringBuffer()
    // Process process = command.execute()
    // process.consumeProcessOutput(out, err)
    // process.waitFor()
    // process.waitForOrKill(1000)
    // println process.text
    // if( sout.size() > 0 ) println out.toString()
    // if( serr.size() > 0 ) println err.toString()

    try {
        sh(returnStdout: true, script: command)
    } catch(Exception ex) {
        installHomebrew(metarunner)
    }

    if (method == 'clean') {
        print "Removing Homebrew!!!"
    }

}

def installHomebrew(metarunner) {
    print "Installing ${metarunner}"
    // new .pedrocesar.utils().installMetarunner(metarunner)
}
