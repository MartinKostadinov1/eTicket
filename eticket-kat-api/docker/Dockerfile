FROM openjdk:11.0.7-jre
VOLUME /tmp
COPY build/libs/eticket.jar app.jar
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://mongo:27017/eticket_kat_api","-jar","/app.jar"]