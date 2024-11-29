package com.rajesh.code.action

import com.rajesh.code.rules.AWS
import com.rajesh.code.rules.Linuxclimain

class Awsaction implements AWS {
    String creds
    String region
    Awsaction(String creds = "aws-own-creds",String region = "eu-west-1"){
        this.creds= creds
        this.region = region
    }
    Linuxclimain obj = new linuxcli()
    @Override
    def init() {
        obj.shellsh('''apt update
apt install curl -y
apt install unzip -y
curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
unzip awscliv2.zip
./aws/install
apt install groff -y
apt install mandoc -y
''')
    }

    @Override
    def command(String command) {
        obj.withAWScreds(creds,region,command)
    }
//    @Override
//    def command(Map sample ,String command) {
//        obj.withcreds(sample , { obj.shellsh(command)})
//    }
}
