package com.rajesh.code.config

import com.rajesh.code.action.linuxcli
import com.rajesh.code.rules.Linuxclimain
import com.rajesh.code.rules.configrule

class configcontrol  implements configrule{
    Linuxclimain obj
    String path
    Map conf
    configcontrol(String path = "configfile.yaml", Linuxclimain obj = new linuxcli() ){
        this.obj = obj
        this.path = path
    }

    def readyaml(){
        def text = obj.libresource(path)
        conf = obj.readyamlfun(text)
        return conf
    }
}