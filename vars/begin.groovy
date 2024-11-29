import com.rajesh.code.steps.*
def call(String sam) {
    def sample = new steps()
    pipeline {
        agent {
            kubernetes {
                defaultContainer 'ubuntu'
                yaml '''
kind: Pod
spec:
  containers:
  - name: ubuntu
    image: ubuntu
    imagePullPolicy: Always
    command:
    - sleep
    args:
    - 99d
'''
            }
        }
        stages {
            stage('init') {
                steps {
                    script {
                        sample.shinvoke("echo 'Welcome, String the pipeline'")
                    }
                }
            }
            stage('checkout') {
                steps {
                    script{
                        sample.gitclone([url: "https://github.com/rajeshmachagiri/application.git",
                        branch: "main",
                        credsID: "github-app-rajesh-jenkins"])
                        sample.shinvoke("pwd")
                        sample.runwithcreds([credsID: "own-creds",pass: "PASS",user: "USER"],{sample.shinvoke("echo $USER")})

                    }
                }
            }
        }
    }
}
