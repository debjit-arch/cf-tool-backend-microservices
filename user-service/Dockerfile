FROM eclipse-temurin:17-jdk
WORKDIR /app

# 1. Copy the TrustStore file into the image
COPY rds-truststore.jks /app/rds-truststore.jks

# 2. Copy the application JAR
COPY target/user-service.jar user-service.jar

EXPOSE 4000

# 3. Add the TrustStore parameters to the java command
ENTRYPOINT ["java", \
            "-Djavax.net.ssl.trustStore=/app/rds-truststore.jks", \
            "-Djavax.net.ssl.trustStorePassword=changeit", \
            "-jar", "user-service.jar"]