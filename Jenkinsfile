pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                // Build steps here, for example:
                sh 'mvn clean install'
            }
        }
        stage('Deploy') {
            steps {
                // Change to the target directory
                sh 'cd target'
                // Start the new service
                sh 'java -jar EpicaWeb-1.0.0-SNAPSHOT.jar'
            }
        }
    }
}
