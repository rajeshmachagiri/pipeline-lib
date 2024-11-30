package com.rajesh.code.action

import com.rajesh.code.rules.AWS
import com.rajesh.code.rules.Dockermain
import com.rajesh.code.rules.Linuxclimain

class Docker implements Dockermain {
    Linuxclimain sample = new linuxcli()
    AWS cloud = new Awsaction()
    String logintoken


    @Override
    def build() {
        sample.shellsh("docker build .")
    }

    @Override
    String ecrgettoken() {
        logintoken = cloud.command {sample.shstdoutput(cloud.ecrlogin)}
        return logintoken
    }


    @Override
    def ecrlogin(String ans) {
        logintoken = ans.trim()
        String command = "set +x ; echo ${logintoken}|docker login --username AWS --password-stdin 247083130299.dkr.ecr.eu-central-1.amazonaws.com"
        sample.shellsh(command)
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
