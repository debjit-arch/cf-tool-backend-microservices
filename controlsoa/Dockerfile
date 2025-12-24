FROM eclipse-temurin:17-jdk
COPY rds-truststore.jks rds-truststore.jks
COPY target/controlsoa-service.jar controlsoa-service.jar
EXPOSE 4005
ENTRYPOINT ["java",\
           "-Djavax.net.ssl.trustStore=rds-truststore.jks", \
           "-Djavax.net.ssl.trustStorePassword=changeit", \
           "-jar", "controlsoa-service.jar"]
