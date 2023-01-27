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
                    def previousBuild = currentBuild.getPreviousBuild()
                    if (previousBuild != null) {
                        if (previousBuild.getResult() == null) {
                            previousBuild.doStop()
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
