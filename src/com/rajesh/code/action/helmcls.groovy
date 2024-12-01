package com.rajesh.code.action

import com.rajesh.code.rules.*


class helmcls implements HELM{
Linuxclimain obj = new linuxcli()
    AWS awsobjmain
    helmcls(awsobj = new Awsaction() ){
        this.awsobjmain = awsobj
    }
    def init(){
        obj.shellsh("echo 'install kubectl ctl , helm")
    }

    def command(){
        String command = "echo 'helm install .....'"
        awsobjmain.command {obj.shellsh(command)}
    }
}
