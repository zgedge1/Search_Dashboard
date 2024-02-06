pipeline{
    agent any

    stages{
        stage('SCM'){
            steps{
                checkout scm
            }
        }
        stage("SonarQube Analysis"){
            def scannerHme = tool 'SonarScanner';
            withSonarQubeEnv(){
                sh "${scannerHme}/bin/sonar-scanner"
            }
        }
    }