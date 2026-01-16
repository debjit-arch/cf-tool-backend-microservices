FROM eclipse-temurin:17-jdk
COPY target/apigateway-service.jar apigateway-service.jar
EXPOSE 8765
ENTRYPOINT ["java", "-jar", "apigateway-service.jar"]
