package com.rajesh.code.action

import com.rajesh.code.rules.AWS
import com.rajesh.code.rules.Dockermain
import com.rajesh.code.rules.Linuxclimain

class Docker implements Dockermain {
    Linuxclimain sample = new linuxcli()
    AWS cloud
    String logintoken


    Docker(AWS cloud = new Awsaction() ){
        this.cloud = cloud
    }

    @Override
    def build() {
        sample.shellsh("docker build -t dockerbuild-test .")
    }

    @Override
    String ecrgettoken() {
        logintoken = cloud.command {sample.shstdoutput("aws ecr get-login-password --region eu-central-1")}
        return logintoken
    }


    @Override
    def ecrlogin(String ans) {
        logintoken = ans.trim()
        String command = "set +x ; echo ${logintoken}|docker login --username AWS --password-stdin *.dkr.ecr.eu-central-1.amazonaws.com"
        sample.shellsh(command)
    }

    @Override
    def push() {
        sample.shellsh(""" docker tag dockerbuild-test:latest ***.dkr.ecr.eu-central-1.amazonaws.com/dockerbuild-test:try-ignore ; 
docker push ***.dkr.ecr.eu-central-1.amazonaws.com/dockerbuild-test:try-ignore """ )
    }

    @Override
    def dockerps() {
        sample.shellsh("docker ps")
    }
}
