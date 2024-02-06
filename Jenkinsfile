pipeline{
    agent any

    stages{
        stage('SCM'){
            checkout scm
        }

        stage("SonarQube Analysis"){
            def mvn = tool "maven";
            withSonarQubeEnv() {
                sh "${mvn}/bin/mvn clean verify sonar:sonar -Dsonar.projectKey=dashboard -Dsonar.projectName='dashboard'"
            }
        }
    }

}