package com.rajesh.code.steps

void shinvoke(String sam) {
        sh sam
}

//void gitclone(String url, String branch,String credsID){
//        checkout scmGit(branches: [[name: "*/${branch}"]], userRemoteConfigs: [[credentialsId: "${credsID}", url: url]])
//}

void gitclone(Map info){
        Map info = info
        if(info.branch == null || info.credsID == null || info.url == null  ) {
                error "info map doesnt have proper values"
        }
        checkout scmGit(branches: [[name: "*/${info.branch}"]], userRemoteConfigs: [[credentialsId: "${info.credsID}", url: info.url]])
}

