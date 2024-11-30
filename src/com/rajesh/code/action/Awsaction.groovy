package com.rajesh.code.action

import com.rajesh.code.rules.AWS
import com.rajesh.code.rules.Linuxclimain

class Awsaction implements AWS {
    Linuxclimain obj = new linuxcli()
    String creds
    String region
    Awsaction(String creds = "aws-own-creds",String region = "eu-west-1",Linuxclimain obj = new linuxcli()){
        this.creds= creds
        this.region = region
        this.obj = obj
    }

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
    def command(Closure sample) {
        obj.withAWScreds(creds,region,sample)
    }


}
