package com.rajesh.code.steps

class stepsdef {

    def stepsContext  // Define a variable to hold the pipeline context

    // Constructor to initialize the pipeline context
    stepsdef(def stepsContext) {
        this.stepsContext = stepsContext
    }

    void shinvoke(String sam) {
        stepsContext.sh sam
    }
}

