FROM adoptopenjdk:11-jdk-hotspot
WORKDIR app
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]