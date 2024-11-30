import com.rajesh.code.action.Awsaction
import com.rajesh.code.action.Docker
import com.rajesh.code.action.Npm
import com.rajesh.code.action.linuxcli
import com.rajesh.code.steps.*
import com.rajesh.code.rules.*
def call(String sam) {
    def obj = new linuxcli()
    def npm = new Npm()
    AWS cloudobj = new Awsaction()
    Dockermain dockerobj = new Docker()
    String ans
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
                        String sample1 = obj.libresource("configfile.yaml")
                        obj.shellsh('echo "sample1"')
                    }
                }
            }
//            stage('build') {
//                steps {
//                    script{
//                          obj.shellsh("ls ; pwd ")
//                          obj.cd("./application/sample-nodejs/", {npm.npmintall()})
//                    }
//                }
//            }
//            stage('Docker') {
//
//                steps {
//                    container('ubuntu'){
//                        script {
//                            cloudobj.init()
//                            obj.shellsh("aws help")
//                            ans = dockerobj.ecrgettoken()
//                        }
//                    }
//                    container('dind') {
//                            script {
//                                  obj.shellsh("docker help")
//                                  dockerobj.ecrlogin(ans)
//                                  obj.shellsh("pwd ; ls")
//                                  obj.cd("./application/sample-nodejs/", { dockerobj.build() })
//                                  dockerobj.push()
//                           }
//                    }
//                }

//            }
        }
    }
}
