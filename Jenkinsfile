pipeline {
    agent any

    parameters {
        string(name: 'SERVICE', defaultValue: 'demo-request', description: 'Microservice to build and deploy')
    }

    environment {
        COMPOSE_DIR  = "/home/ubuntu"
        IMAGE_TAG    = "latest"
        DOCKER_USER  = ''  // Will be set via credentials binding
        DOCKER_PASS  = ''  // Will be set via credentials binding
    }

    stages {
        stage('Checkout Code') {
            steps {
                git url: 'https://github.com/debjit-arch/cf-tool-backend-microservices.git',
                    branch: 'main'
            }
        }

        stage('Build JAR') {
            steps {
                dir("${params.SERVICE}") {
                    sh 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                dir("${params.SERVICE}") {
                    script {
                        def imageName = "tkos007/${params.SERVICE}"
                        sh "docker build -t ${imageName}:${IMAGE_TAG} ."
                    }
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
                    script {
                        def imageName = "tkos007/${params.SERVICE}"
                        sh '''
                            echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin
                            docker push ${imageName}:${IMAGE_TAG}
                        '''
                    }
                }
            }
        }

        stage('Deploy using docker-compose') {
            steps {
                sh '''
                    cd ${COMPOSE_DIR}
                    docker compose pull ${params.SERVICE}
                    docker compose up -d --scale ${params.SERVICE}=2 ${params.SERVICE}
                '''
            }
        }
    }
}
