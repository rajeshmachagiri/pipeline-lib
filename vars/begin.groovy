import com.rajesh.code.action.Npm
import com.rajesh.code.action.linuxcli
import com.rajesh.code.steps.*
def call(String sam) {
    def obj = new linuxcli()
    def npm = new Npm()
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
                        obj.shellsh("echo 'Welcome, String the pipeline'")
                        obj.shellsh("touch sample-doc")
                    }
                }
            }
            stage('checkout') {
                steps {
                    script {
                        obj.shellsh("ls ; pwd ")
                        obj.gitcheckout([url    : "https://github.com/rajeshmachagiri/application.git",
                                         branch : "main",
                                         credsID: "github-app-rajesh-jenkins"])
//                        Closure command = {obj.shellsh('echo $PASS')}
//                        obj.withcreds([credsID: "own-creds",pass: "PASS",user: "USER"], command)
                    }
                }
            }
            stage('build') {
                steps {
                    script{
                          obj.shellsh("ls ; pwd ")
                          obj.shellsh(cd ./application/sample-nodejs/)
                          npm.npmintall()
//                        Closure command = {obj.shellsh('echo $PASS')}
//                        obj.withcreds([credsID: "own-creds",pass: "PASS",user: "USER"], command)
                    }
                }
            }
        }
    }
}
