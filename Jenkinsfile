pipeline{
    agent any

    stages{
        stage('SCM'){
            steps{
                checkout scm
            }
        }
    }
      stage('SonarQube Analysis') {
        def mvn = tool 'Default Maven';
        withSonarQubeEnv() {
            sh "${mvn}/bin/mvn clean verify sonar:sonar -Dsonar.projectKey=Dashboard -Dsonar.projectName='Dashboard'"
    }
  }
}

