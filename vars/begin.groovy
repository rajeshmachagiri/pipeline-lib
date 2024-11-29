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
    securityContext:
      privileged: true
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
                          obj.shellsh('''apt update
apt install curl -y
apt install unzip -y
curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
unzip awscliv2.zip
./aws/install
apt install groff -y
apt install mandoc -y
''')
                        cloudobj.command("aws ecr get-login-password --region eu-central-1")
//                        Closure command = {obj.shellsh('echo $PASS')}
//                        obj.withcreds([credsID: "own-creds",pass: "PASS",user: "USER"], command)
                    }
                }
            }
            stage('Docker') {

                steps {
                    container('dind') {
                            script {
//                                obj.shellsh("aws help")
//                                cloudobj.command("aws s3 ls")
//                                obj.shellsh("docker help")
                                obj.shellsh("docker pull ubuntu")
                                obj.shellsh("docker tag ubuntu 247083130299.dkr.ecr.eu-central-1.amazonaws.com/dockerbuild-test:latest")
                                obj.shellsh("docker push 247083130299.dkr.ecr.eu-central-1.amazonaws.com/dockerbuild-test:try-ignore")

                                dockerobj.dockerinit()

//                            obj.cd("./application/sample-nodejs/", { npm.npmintall() })
//
                           }
                    }
                }

            }
        }
    }
}
