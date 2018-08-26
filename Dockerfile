FROM openjdk:8-jdk-alpine

# Add Maintainer Info
LABEL maintainer="ricardo.palazzio@gmail.com"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The application's jar file
ARG JAR_FILE=target/envers-1.jar

# Add the application's jar to the container
ADD ${JAR_FILE} envers-1.jar

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom -d64 -Xms256m -Xmx256m","-jar","/envers-1.jar"]