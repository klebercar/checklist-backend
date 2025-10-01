# Build
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -B -q -DskipTests dependency:go-offline
COPY src ./src
# força repackage para garantir Boot JAR executável
RUN mvn -B -DskipTests clean package spring-boot:repackage

# Runtime
FROM eclipse-temurin:21-jre
WORKDIR /app
# copia o JAR reempacotado
COPY --from=build /app/target/checklist-0.0.1.jar app.jar
ENV APP_PORT=$PORT
EXPOSE 8080
CMD ["java","-jar","/app/app.jar"]