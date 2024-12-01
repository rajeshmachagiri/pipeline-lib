package com.rajesh.code

import com.rajesh.code.action.Docker
import com.rajesh.code.config.configcontrol
import com.rajesh.code.rules.AWS
import com.rajesh.code.rules.Configrule
import com.rajesh.code.rules.Dockermain

class Factory {
    static def init(){
        Configrule sam = new configcontrol()
        Map config = sam.readyamlfun()
        config = sam.getcredsconfig(config)
//        awsobj = config[account]
//        return awsobj
        return config
    }

    static AWS getawsobj(String account){
        Map temp = init()
        return temp[account]
    }

    static Dockermain getdocobj(AWS sam){
        return new Docker(sam)
    }

}
