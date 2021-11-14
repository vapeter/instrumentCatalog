FROM adoptopenjdk:11-jre-hotspot as builder
WORKDIR app
COPY ./target/*.jar instrumentCatalog.jar
EXPOSE 9100
RUN java -Djarmode=layertools -jar instrumentCatalog.jar extract

FROM adoptopenjdk:11-jre-hotspot
WORKDIR app
COPY --from=builder app/dependencies/ ./
COPY --from=builder app/spring-boot-loader/ ./
COPY --from=builder app/snapshot-dependencies/ ./
COPY --from=builder app/application/ ./
ENTRYPOINT ["java", \
  "org.springframework.boot.loader.JarLauncher"]