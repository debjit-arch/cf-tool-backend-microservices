FROM eclipse-temurin:17-jdk
COPY target/naming-server.jar naming-server.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "naming-server.jar"]
