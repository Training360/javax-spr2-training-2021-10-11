#FROM adoptopenjdk:11-jdk-hotspot
#WORKDIR app
#COPY target/*.jar app.jar
#ENTRYPOINT ["java", "-jar", "app.jar"]

FROM adoptopenjdk:11-jre-hotspot as builder
WORKDIR application
COPY target/*.jar app.jar
RUN java -Djarmode=layertools -jar app.jar extract


FROM adoptopenjdk:11-jre-hotspot
WORKDIR app
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
ENTRYPOINT ["java", \
  "org.springframework.boot.loader.JarLauncher"]