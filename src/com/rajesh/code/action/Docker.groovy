package com.rajesh.code.action

import com.rajesh.code.rules.Dockermain
import com.rajesh.code.rules.Linuxclimain

class Docker implements Dockermain {
Linuxclimain sample = new linuxcli()
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