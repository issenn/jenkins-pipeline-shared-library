#!groovy

import com.issenn.jenkins.meta.Constants

def call(args) {
    def maintainer = args
    if (args == null || (args instanceof String && args.trim().isEmpty())) {
        maintainer = Constants.DEFAULT_MAINTAINER_NAME
    }
    echo "Project maintained by $maintainer"
}
