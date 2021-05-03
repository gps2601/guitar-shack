FROM openjdk:11
ARG JAR_FILE="build/libs/guitar-shack-1.0-SNAPSHOT.jar"
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Djava.security.edg=file:/dev/./urandom","-jar","/app.jar"]
