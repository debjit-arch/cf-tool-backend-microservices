pipeline {
    agent any

    parameters {
        string(name: 'SERVICE',
               defaultValue: 'demo-service',
               description: 'docker-compose service name')

        string(name: 'SERVICE_DIR',
               defaultValue: 'demo-request',
               description: 'Folder name inside the monorepo')

        string(name: 'IMAGE_TAG',
               defaultValue: 'latest',
               description: 'Docker image tag')

        string(name: 'SCALE',
               defaultValue: '2',
               description: 'Number of containers to run')
    }

    environment {
        COMPOSE_DIR = "/home/ubuntu"
        DOCKER_REPO = "tkos007"
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
                dir("${params.SERVICE_DIR}") {
                    sh 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                dir("${params.SERVICE_DIR}") {
                    script {
                        env.IMAGE_NAME = "${DOCKER_REPO}/${params.SERVICE}"
                        sh "docker build -t ${env.IMAGE_NAME}:${params.IMAGE_TAG} ."
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
                    sh """
                        echo \$DOCKER_PASS | docker login -u \$DOCKER_USER --password-stdin
                        docker push ${env.IMAGE_NAME}:${params.IMAGE_TAG}
                    """
                }
            }
        }

        stage('Deploy using docker-compose') {
            steps {
                sh """
                    cd ${COMPOSE_DIR}
                    # Stop and remove existing containers for the service
                    docker compose stop ${params.SERVICE}
                    docker compose rm -f ${params.SERVICE}
                    docker compose pull ${params.SERVICE}
                    docker compose up -d \
                      --scale ${params.SERVICE}=${params.SCALE} \
                      ${params.SERVICE}
                """
            }
        }
    }
}
