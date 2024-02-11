pipeline{
    agent any

    stages{
        stage('SCM'){
            steps{
                checkout scm
            }
        }
        stage("SonarQube Analysis"){
            steps{
                script{
                    def scannerHme = tool 'sonar_scanner_1';
                    withSonarQubeEnv(){

                        sh "${scannerHme}/bin/sonar-scanner"
                }    
            }
            }
        }
    }
}