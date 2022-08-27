# base image to build a JRE
FROM maven:3.8.6

WORKDIR /app

COPY . /app

RUN mvn package -DskipTests=true

# main app image
FROM openjdk:18

WORKDIR /app

COPY --from=0 /app/target/digitivation-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

CMD ["java", "-jar", "digitivation-0.0.1-SNAPSHOT.jar"]
