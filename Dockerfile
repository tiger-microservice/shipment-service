# Stage 1: build
# Start with a Maven image that includes JDK 21
FROM maven:3.9.8-amazoncorretto-21 AS build

# Copy source code and pom.xml file to /app folder
WORKDIR /app
COPY pom.xml .
COPY libs ./libs
COPY src ./src

# Build source code with maven
RUN mvn install:install-file -Dfile=./libs/common-utils-1.0.0.jar -DgroupId=com.tiger -DartifactId=common-utils -Dversion=1.0.0 -Dpackaging=jar
RUN mvn package -DskipTests

# Stage 2: create image
# Start with Amazon Correto JDK 21
FROM amazoncorretto:21.0.4

# Set working folder to App and copy complied file from above step
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]