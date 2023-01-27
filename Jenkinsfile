pipeline {
    agent any
    tools {
        maven 'Maven 3.8.7'
    }
    stages {
       
        stage('Build') {
            steps {
                script {
                    currentBuild.getPreviousBuild().doStop()  // Interrompe o build anterior
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
