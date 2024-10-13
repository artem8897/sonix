FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /build/
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests

FROM eclipse-temurin:17-jdk-jammy
COPY --from=build /build/target/sonix-0.0.1-SNAPSHOT.jar /app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]