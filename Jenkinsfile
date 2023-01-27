pipeline {
    agent any
    tools {
        maven 'Maven 3.8.7'
    }
    stages {
        stage('Build') {
            steps {
                // Build steps here, for example:
                sh 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                // Test steps here, for example:
                sh 'mvn test'
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
