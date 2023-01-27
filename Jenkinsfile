pipeline {
    agent any
    tools {
        maven 'Maven 3.8.7'
    }
    stages {
       
        stage('Build') {
            steps {
                script {
                    build job: 'Gerenciamento epicaweb-api', parameters: [], wait: false, propagate: false, abort: true                
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
