package com.rajesh.code.rules

interface Linuxclimain {

    def shellsh(String sam)

    def errorinfo(String sam)

    def gitcheckout(Map config)

    def withcreds(Map creds, Closure dummy)

    def cd(String sam , Closure dummy)

    def withAWScreds(String sam, String region, String command)
}