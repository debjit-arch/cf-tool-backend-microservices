FROM eclipse-temurin:17-jdk
COPY rds-truststore.jks rds-truststore.jks
COPY target/doc-service.jar doc-service.jar
EXPOSE 4002
ENTRYPOINT ["java",\
           "-Djavax.net.ssl.trustStore=rds-truststore.jks", \
           "-Djavax.net.ssl.trustStorePassword=changeit", \
           "-jar", "doc-service.jar"]
