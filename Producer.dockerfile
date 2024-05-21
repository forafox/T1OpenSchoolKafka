FROM gradle:8.2.0-jdk17-alpine AS builder
WORKDIR /home/gradle
COPY ./ ./
RUN ./gradlew bootJar -DskipTests

FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=builder  /home/gradle/producer/build/libs/producer-1.0.jar /app/producer.jar
ENTRYPOINT ["java","-jar","/app/producer.jar"]