## BUILD STAGE
# Use an official Maven image as the base image
FROM maven:3.9.9-amazoncorretto-17-alpine AS build

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml and the project files to the container
COPY pom.xml .
COPY src ./src
# Build the application using Maven
RUN mvn clean package

## PACKAGE STAGE (use jre inside jdk to execute jar file)

# Use an official OpenJDK image as the base image
FROM amazoncorretto:17-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file from the previous stage to the container
COPY --from=build /app/target/life-1.0.jar ./

# Set the command to run the application
#you could replace CMD with ENTRYPOINT (that is more specific)
#see https://stackoverflow.com/questions/21553353/what-is-the-difference-between-cmd-and-entrypoint-in-a-dockerfile
CMD ["java", "-jar", "life-1.0.jar"]