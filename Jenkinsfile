pipeline {
    agent any
    tools {
        maven 'Maven 3.8.1'
        jdk 'Java 17'
        dockerTool 'Docker'
    }
    environment {
        DOCKER_REGISTRY = 'malekkn'
        APP_NAME = 'survey'
        DOCKER_CREDENTIALS = credentials('docker-hub-credentials')

    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build') {
            steps {
                script {
                    sh 'mvn clean package -DskipTests'
                }
            }
        }
        stage('Docker Build') {
            steps {
                script {
                    sh "docker build -t ${DOCKER_REGISTRY}/${APP_NAME}:${BUILD_NUMBER} ."
                }
            }
        }
        stage('Docker Push') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', usernameVariable: 'Malek Noura', passwordVariable: 'dockerhub098')]) {
                        sh "echo ${DOCKER_PASS} | docker login -u ${DOCKER_USER} "
                        sh "docker login"
                        sh "docker push ${DOCKER_REGISTRY}/${APP_NAME}:${BUILD_NUMBER}"
                        sh "docker push ${DOCKER_REGISTRY}/${APP_NAME}:latest"
                    }
                }
            }
        }
        stage('Deploy') {
            steps {
                script {
                    sh "docker-compose down || true"
                    sh "docker-compose up -d"
                }
            }
        }
    }
    post {
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed. Check the logs for details.'
        }
        always {
            script {
                sh 'docker system prune -f || true'
            }
        }
    }
}