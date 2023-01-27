pipeline{
    stage("Executar aplicação") {
        steps {
            bat '''
            cd target
            java -jar EpicaWeb-1.0.0-SNAPSHOT.jar
            '''
        }
    }
}
