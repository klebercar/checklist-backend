FROM eclipse-temurin:21-jre
WORKDIR /app
COPY target/checklist-0.0.1.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
