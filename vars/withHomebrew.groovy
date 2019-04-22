#!/usr/bin/env groovy


def call(method=null, Closure body={}) {
    def metarunner = 'brew'

    print "Setting up Homebrew!"


    println "-----"
    // def out = new StringBuilder(), err = new StringBuilder()
    def out = new StringBuffer()
    def err = new StringBuffer()

    def command = "command -v ${metarunner}"
    println command
    Process process = command.execute()
    process.consumeProcessOutput(out, err)
    // process.waitFor()
    process.waitForOrKill(1000)
    println process.text
    // if( sout.size() > 0 )
    println out.toString()
    // if( serr.size() > 0 )
    println err.toString()
    println "-----"

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
