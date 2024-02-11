pipeline {
    agent any
    
    parameters {
        string(name: 'SONAR_JAVA_BINARIES', defaultValue: '/home/zach/Documents/javacode/dashboardapp/src/main/java/com/dashboard', description: 'Path to compiled Java classes')
    }

    stages {
        stage('SonarQube analysis') {
            steps {
                script {
                    def scannerHome = tool 'sonar_scanner_1'
                    withSonarQubeEnv('SonarQube') {
                        sh "${scannerHome}/bin/sonar-scanner -Dsonar.java.binaries=${params.SONAR_JAVA_BINARIES}"
                    }
                }
            }
        }
        // Other stages
    }
}
