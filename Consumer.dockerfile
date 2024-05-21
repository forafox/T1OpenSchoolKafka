FROM gradle:8.2.0-jdk17-alpine AS builder
WORKDIR /home/gradle
COPY ./ ./
RUN ./gradlew bootJar -DskipTests

FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=builder  /home/gradle/consumer/build/libs/consumer-1.0.jar /app/consumer.jar
ENTRYPOINT ["java","-jar","/app/consumer.jar"]