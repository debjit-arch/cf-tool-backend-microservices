FROM eclipse-temurin:17-jdk
COPY rds-truststore.jks rds-truststore.jks
COPY target/demo-service.jar demo-service.jar
EXPOSE 4001
ENTRYPOINT ["java",\
           "-Djavax.net.ssl.trustStore=rds-truststore.jks", \
           "-Djavax.net.ssl.trustStorePassword=changeit", \
           "-jar", "demo-service.jar"]
