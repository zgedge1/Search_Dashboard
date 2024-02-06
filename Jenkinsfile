pipeline{
    agent any

    stages{
        stage('SCM'){
            steps {
                checkout scm
            }

        }

        stage("SonarQube Analysis"){
            steps {

                def mvn = tool "maven";
                withSonarQubeEnv() {
                    sh "${mvn}/bin/mvn clean verify sonar:sonar -Dsonar.projectKey=dashboard -Dsonar.projectName='dashboard'"
            }
            }
        }
    }

}