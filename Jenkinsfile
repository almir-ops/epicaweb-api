pipeline {
    agent any
    tools {
        maven 'Maven 3.8.7'
    }
    stages {
       
        stage('Build') {
            steps {
                script {
                    // Abort previous build if there is one
                    currentBuild = currentBuild.rawBuild
                    previousBuilds = currentBuild.getPreviousBuilds()
                    if (previousBuilds.size() > 0) {
                        previousBuilds[0].getExecutor().interrupt()
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
