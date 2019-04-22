#!/usr/bin/env groovy


def call(method=null, Closure body={}) {
    def metarunner = 'brew'

    print "Setting up Homebrew!"


    def sout = new StringBuilder(), serr = new StringBuilder()
    // def out = new StringBuffer()
    // def err = new StringBuffer()

    def command = "command -v ${metarunner}"
    Process process = command.execute()
    process.consumeProcessOutput(sout, serr)
    // process.waitFor()
    process.waitForOrKill(1000)
    println process.text
    if( sout.size() > 0 ) println sout.toString()
    if( serr.size() > 0 ) println serr.toString()

    if (!"command -v ${metarunner}".execute().text.trim()) {
        print("command -v ${metarunner}".execute().text.trim())
        installHomebrew(metarunner)
    }

    if (!fileExists("/usr/local/bin/brew")) {
        print("not found")

    } else {
        print("found")
    }


    if (method == 'clean') {
        print "Removing Homebrew!!!"
    }

}

def installHomebrew(metarunner) {
    print "Installing ${metarunner}"
    // new .pedrocesar.utils().installMetarunner(metarunner)
}
