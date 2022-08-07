# digitivation

Digital wedding invitation application.
Request a digital wedding invitation with couple and events information.
We will prepare a digital invitation for you to share with your family and friends. 

## Getting Started

Before starting the application you should run the dependencies with docker-compose.
```bash
docker compose up
```

Run the application
```bash
chmod +x ./mvnw && ./mvnw package
jar -jar /target/{application_name_version}.jar
```

Build and run the container
```bash
docker build -t digitivation .
# run the container
docker run -p 8080:8080 digitivation
```
