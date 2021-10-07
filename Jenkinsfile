pipeline {
    agent any

    tools {
        gradle "Gradle 7.1.1"
    }

    stages {

        stage('Checkout') {
            steps {
                // Get some code from a GitHub repository
                git branch: 'develop', url: 'https://github.com/enavarrom/operatortest.git'
            }
        }

        stage('Build') {
            steps {
                sh "gradle clean build -x test"
            }
        }

        stage('Test') {
            steps {
                sh "gradle test"
            }
        }

        stage('Analyze & Coverage') {
            steps {
                echo "Analyze & Coverage"
            }
        }

        stage('Build Image') {
            steps {
                 sh "gradle bootBuildImage"
            }
        }

        stage('Deploy') {
            steps {
                echo "Deploying in ....."
            }
        }
    }
}
