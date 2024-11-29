package com.rajesh.code.steps

void shinvoke(String sam) {
        sh sam
}

void shstdout(String sam) {
        sh returnStdout: true, script: sam
}

void gitclone(Map info){
        Map infoconfig = info
        if(infoconfig.branch == null || infoconfig.credsID == null || infoconfig.url == null  ) {
                error "info map doesnt have proper values"
        }
        checkout scmGit(branches: [[name: "*/${info.branch}"]], userRemoteConfigs: [[credentialsId: "${info.credsID}", url: info.url]])
}

void errorthrow(String sam){
        error sam
}

void runwithcreds(Map creds, Closure sample ){
        if(creds.credsID == null || creds.pass == null || creds.user == null ){
                error "need more info here"
        }
        withCredentials([usernamePassword(credentialsId: creds.credsID, passwordVariable: creds.pass, usernameVariable: creds.user)]) {
                sample.call()
        }
}

void cd(String sam,Closure sample){
        dir(sam) {
                sample.call()
        }
}

void withAWScreds(String creds, String region, Closure sample) {
        withAWS(credentials: creds, region: region) {
                sample()
        }
}
