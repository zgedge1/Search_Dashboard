pipeline {
    agent any
    
    stages{
        stage('SCM'){
            steps{
                checkout scm
            }
        }

        stage ('SonarQube Analysys') {
            def scannerHome = tool 'SonarQube'
            withSonarQubeEnv() {
                sh "sh ${scannerHome}/bin/sonar-scanner"
            }
        }
    }
}