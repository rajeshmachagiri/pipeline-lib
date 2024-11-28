package com.rajesh.code.steps

void shinvoke(String sam) {
        sh sam
}

//void gitclone(String url, String branch,String credsID){
//        checkout scmGit(branches: [[name: "*/${branch}"]], userRemoteConfigs: [[credentialsId: "${credsID}", url: url]])
//}

void gitclone(Map info){
        Map infoconfig = info
        if(infoconfig.branch == null || infoconfig.credsID == null || infoconfig.url == null  ) {
                error "info map doesnt have proper values"
        }
        checkout scmGit(branches: [[name: "*/${info.branch}"]], userRemoteConfigs: [[credentialsId: "${info.credsID}", url: info.url]])
}

