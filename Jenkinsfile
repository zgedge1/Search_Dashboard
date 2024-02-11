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
                    withSonarQubeEnv()
                    sh "${mvn}/bin/mvn clean verify sonar:sonar -Dsonar.projectKey=Dashboard -Dsonar.projectName='Dashboard'"
                }
            }
        }
    }
}