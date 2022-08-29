FROM maven:3.6.0-jdk-11 AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src//app
RUN mvn -B package --file /usr/src/app/pom.xml

FROM openjdk:11
EXPOSE 8091
COPY --from=build /usr/src/app/target/ms-debitcard-0.0.1-SNAPSHOT.jar /usr/app/ms-debitcard-0.0.1-SNAPSHOT.jar
ADD target/ms-debitcard-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/usr/app/ms-debitcard-0.0.1-SNAPSHOT.jar"]