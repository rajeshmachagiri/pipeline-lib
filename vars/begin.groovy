def call(String sam) {
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
            stage('Example') {
                steps {
                    script{
                        def sample = new steps()
                        sample.sh("echo devil")
                    }
                }
            }
        }
    }
}
