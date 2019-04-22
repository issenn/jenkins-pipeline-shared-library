#!/usr/bin/env groovy


def call(method=null, Closure body={}) {
    def metarunner = 'brew'

    print "Setting up Homebrew!"

    if (!fileExists("command -v ${metarunner}".execute().text.trim())) {
        print("command -v ${metarunner}".execute().text.trim())
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
