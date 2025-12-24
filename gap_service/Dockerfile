FROM eclipse-temurin:17-jdk

COPY rds-truststore.jks rds-truststore.jks
COPY target/gap-service.jar gap-service.jar
EXPOSE 4004
ENTRYPOINT ["java",\
           "-Djavax.net.ssl.trustStore=rds-truststore.jks", \
           "-Djavax.net.ssl.trustStorePassword=changeit", \
		   "-jar",  "gap-service.jar"]
