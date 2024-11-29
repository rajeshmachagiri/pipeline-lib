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
  - name: dind
    image: docker:dind
    securityContext:
      privileged: true # Required for dind
    env:
     - name: DOCKER_TLS_CERTDIR
       value: ""
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
                          obj.cd("./application/sample-nodejs/", {npm.npmintall()})
//                        Closure command = {obj.shellsh('echo $PASS')}
//                        obj.withcreds([credsID: "own-creds",pass: "PASS",user: "USER"], command)
                    }
                }
            }
            stage('Docker') {

                steps {
                    container(dind) {
                            script {
                                obj.shellsh("docker help ")
//                            obj.cd("./application/sample-nodejs/", { npm.npmintall() })
//
                           }
                    }
                }

            }
        }
    }
}
