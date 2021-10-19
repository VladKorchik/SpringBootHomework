FROM openjdk:11
EXPOSE 8081
ADD target/SpringBootHomework-0.0.1-SNAPSHOT.jar SpringBootHomework-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/SpringBootHomework-0.0.1-SNAPSHOT.jar"]