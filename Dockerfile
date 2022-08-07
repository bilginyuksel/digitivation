# base image to build a JRE
FROM amazoncorretto:18

WORKDIR /app

COPY . /app

RUN chmod +x mvnw && sh mvnw package

# main app image
FROM openjdk:18

WORKDIR /app

COPY --from=0 /app/target/digitivation-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

CMD ["java", "-jar", "digitivation-0.0.1-SNAPSHOT.jar"]