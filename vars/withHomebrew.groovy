#!/usr/bin/env groovy


def call(version='2.1.1', method=null, Closure body={}) {
    def metarunner = 'brew'

    print "Setting up Homebrew!"

    if (!"command -v ${metarunner}".execute().text.trim()) {
        installHomebrew(metarunner)
    }

}

def installHomebrew(metarunner) {
    print "Installing ${metarunner}"
    // new .pedrocesar.utils().installMetarunner(metarunner)
}
