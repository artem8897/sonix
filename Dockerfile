 FROM maven:3.9.4-eclipse-temurin-17 AS build
 WORKDIR /build/
 COPY pom.xml .
 RUN mvn dependency:go-offline
 COPY src ./src
 RUN mvn package -DskipTests

 FROM eclipse-temurin:17-jdk-jammy
 ARG JAR_FILE=target/sonix-0.0.1-SNAPSHOT.jar
 COPY --from=build /build/$JAR_FILE /demo/app.jar
 EXPOSE 8080
 ENTRYPOINT ["java", "-jar", "/demo/app.jar"]