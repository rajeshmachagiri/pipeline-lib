package com.rajesh.code.config

import com.rajesh.code.action.Awsaction
import com.rajesh.code.action.linuxcli
import com.rajesh.code.rules.Linuxclimain
import com.rajesh.code.rules.Configrule

class configcontrol implements Configrule{
    Linuxclimain obj
    String path
    Map conf
    configcontrol(String path = "configfile.yaml", Linuxclimain obj = new linuxcli() ){
        this.obj = obj
        this.path = path
    }

    def readyamlfun(){
        def text = obj.libresource(path)
        conf = obj.readyamlfun(text)
        return conf
    }

    def getcredsconfig(Map aws){
        Map res = [:]
        aws["aws"].each {k,v ->
            Awsaction sam = new Awsaction(v["credsID"],v["region"])
            res.put(k,sam)
        }
        return res
    }

}