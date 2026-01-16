FROM eclipse-temurin:17-jdk
WORKDIR /app

# 1. Copy the TrustStore file into the image
COPY rds-truststore.jks /app/rds-truststore.jks
COPY target/task-service.jar task-service.jar
EXPOSE 4006

ENTRYPOINT ["java",\
           "-Djavax.net.ssl.trustStore=/app/rds-truststore.jks", \
           "-Djavax.net.ssl.trustStorePassword=changeit", \
		   "-jar", "task-service.jar"]
