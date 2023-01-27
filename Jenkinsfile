pipeline {
    agent any
    tools {
        maven 'Maven 3.8.7'
    }
    stages {
       
        stage('Build') {
            steps {
              script {
                    def currentBuild = currentBuild.rawBuild
                    def previousBuilds = currentBuild.getPreviousBuild()
                    if (previousBuilds.size() > 0) {
                        for (def build in previousBuilds) {
                            if (build.getResult() == null) {
                                build.doStop()
                            }
                        }
                    }
                }
                // Build steps here, for example:
                bat "mvn clean install"
            }
        }
        stage('Execute Jar'){
            steps{
                 bat "java -jar target/EpicaWeb-1.0.0-SNAPSHOT.jar"
            }
        }
    }
}
