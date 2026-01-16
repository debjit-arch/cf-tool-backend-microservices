FROM eclipse-temurin:17-jdk
# We keep the JKS copy for DocumentDB access
COPY rds-truststore.jks rds-truststore.jks
COPY target/gws-service.jar gws-service.jar
EXPOSE 8080
# Use a simple entrypoint without the trustStore overrides
ENTRYPOINT ["java", "-jar", "gws-service.jar"]