FROM maven:3.8.4-eclipse-temurin-11-alpine AS MAVEN_BUILD

COPY ./ ./

RUN mvn clean package

FROM openjdk:17-jdk-alpine3.12

COPY --from=MAVEN_BUILD /target/ /

CMD ["java", "-jar", "/JpaLayer-1.0.jar"]