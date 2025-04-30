# Dockerfile

# Use an official JDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Add a volume for logs (optional)
VOLUME /tmp

# Copy the app JAR to the container
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Run the JAR file
ENTRYPOINT ["java","-jar","/app.jar"]
