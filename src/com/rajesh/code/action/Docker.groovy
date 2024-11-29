package com.rajesh.code.action

import com.rajesh.code.rules.Dockermain
import com.rajesh.code.rules.Linuxclimain

class Docker implements Dockermain {
Linuxclimain sample = new linuxcli()

    @Override
    def dockerinit() {
        sample.shellsh('''apt update
                # Install Docker
                apt-get update
                apt-get install -y \\
                    apt-transport-https \\
                    ca-certificates \\
                    curl \\
                    software-properties-common
                curl -fsSL https://download.docker.com/linux/ubuntu/gpg | apt-key add -
                add-apt-repository \\
                    "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"
                apt-get update
                apt-get install -y docker-ce
''')
    }

    @Override
    def build() {
        sample.shellsh("docker build .")
    }

    @Override
    def push() {
        sample.shellsh("docker tag ${}")
        sample.withcreds([credsID: sample , user: "demo" , pass: 123],{docker push})
    }

    @Override
    def dockerps() {
        return null
    }
}
