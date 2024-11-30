package com.rajesh.code.rules

interface AWS {

    def init()

    def command(Closure command)

}