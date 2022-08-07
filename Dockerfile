# base image to build a JRE
FROM amazoncorretto:18

WORKDIR /app

COPY . /app

RUN chmod +x mvnw && sh mvnw package

# main app image
FROM openjdk:18-alpine
WORKDIR /app
COPY --from=0 target/digitivation-0.0.1-SNAPSHOT.jar ./
EXPOSE 8080
CMD ["java", "-jar", "awesome-app-1.0.jar"]
#FROM alpine:latest
#
#WORKDIR /app
#
## copy JRE from the base image
#COPY --from=0 /target/digitivation-0.0.1-SNAPSHOT.jar /app
#
#EXPOSE 8080
#ENTRYPOINT [ "/jre/bin/java", "-jar", "/app/digitivation-0.0.1-SNAPSHOT.jar" ]