package com.rajesh.code.action

import com.rajesh.code.rules.Npmmain

class Npm implements Npmmain {
def obj = new linuxcli()
    @Override
    def npmrun() {
        obj.shellsh("npm run")
    }

    @Override
    def npmintall() {
        obj.shellsh('echo "npm install"')
    }

    @Override
    def npmtest() {
        obj.shellsh('echo "npm run test"')
    }
}
