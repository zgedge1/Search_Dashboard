pipeline {
    agent any

    stages {
        stage('SCM') {
            steps {
                checkout scm
            }
        }

        stage('SonarQube Analysis'){
            steps {
                script {
                    def mvn =tool 'maven1'
                    withSonarQubeEnv('192.168.1.210:9000')
                    sh "${mvn}/bin/mvn clean verify sonar:sonar -Dsonar.projectKey=Dashboard -Dsonar.projectName='Dashboard'"
                }
            }
        }
    }
}