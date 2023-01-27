pipeline {
    agent any
    tools {
        maven 'Maven 3.8.7'
    }
    stages {
       
        stage('Build') {
            steps {
                // Build steps here, for example:
                bat "mvn clean install"
            }
        }
        stage('Execute Jar'){
            steps{
                 bat "cd target"
                 bat "java -jar EpicaWeb-1.0.0-SNAPSHOT.jar"
            }
        }
    }
}
