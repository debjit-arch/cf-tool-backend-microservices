pipeline {
    agent any

    environment {
        SERVICE_NAME = "demo-service"
        IMAGE_NAME   = "tkos007/demo-service"
        IMAGE_TAG    = "latest"
        COMPOSE_DIR  = "/home/ubuntu"
    }

    stages {

        stage('Checkout Code') {
            steps {
                git(
                    url: 'https://github.com/debjit-arch/cf-tool-backend-microservices.git',
                    branch: 'main',
                    credentialsId: 'github-creds'
                )
            }
        }

        stage('Build JAR (demo-service)') {
            steps {
                dir('demo-service') {
                    sh 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build Docker Image (demo-service)') {
            steps {
                dir('demo-service') {
                    sh 'docker build -t $IMAGE_NAME:$IMAGE_TAG .'
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-creds',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    sh '''
                        echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin
                        docker push $IMAGE_NAME:$IMAGE_TAG
                    '''
                }
            }
        }

        stage('Deploy demo-service using docker-compose') {
            steps {
                sh '''
                    cd $COMPOSE_DIR
                    docker-compose pull $SERVICE_NAME
                    docker-compose up -d $SERVICE_NAME
                '''
            }
        }
    }
}
