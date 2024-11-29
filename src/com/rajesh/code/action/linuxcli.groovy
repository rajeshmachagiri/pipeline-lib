package com.rajesh.code.action

import com.rajesh.code.rules.*
import com.rajesh.code.steps.*

class linuxcli implements Linuxclimain {
    def invo = new steps()

    @Override
    def shellsh(String sam) {
        invo.shinvoke( sam )
    }

    @Override
    def errorinfo(String sam) {
        invo.errorthrow(sam)
    }

    @Override
    def gitcheckout(Map config) {
        invo.gitclone(config)
    }

    @Override
    def withcreds(Map creds , Closure dummy) {
        invo.runwithcreds(creds,dummy)
    }

    @Override
    def cd(String sam, Closure dummy) {
        invo.cd(sam, dummy)
    }

    @Override
    def withAWScreds(String creds, String region, String command) {
        invo.withAWScreds(creds,region,command)
    }
}

