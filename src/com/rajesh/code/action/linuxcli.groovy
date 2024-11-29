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
    def error(String sam) {
        invo.error( sam )
    }

    @Override
    def gitcheckout(Map config) {
        invo.gitclone(config)
    }

    @Override
    def withcreds(Map creds , Closure dummy) {
        invo.runwithcreds(creds,dummy)
    }

}
