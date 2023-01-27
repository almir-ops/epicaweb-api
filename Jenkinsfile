pipeline {
    agent any
    tools {
        maven 'Maven 3.8.7'
    }
    stages {
        stage('Cancel current job'){
             if (currentBuild.rawBuild.getCause(hudson.model.Cause.UpstreamCause) != null) {
                        currentBuild.rawBuild.getCause(hudson.model.Cause.UpstreamCause).getUpstreamBuild().getExecutor().interrupt()
             }
        }
        stage('Build Jar') {
            steps {
                // Build steps here, for example:
                bat "mvn clean install"
            }
        }
        stage('Execute Jar'){
            bat "cd target"
            bat "java -jar EpicaWeb-1.0.0-SNAPSHOT.jar"
        }
    }
}
