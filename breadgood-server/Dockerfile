# COPY --chown=gradle:gradle . /home/gradle/src
# WORKDIR /home/gradle/src
# RUN gradle build --no-daemon 
FROM adoptopenjdk/openjdk11:alpine-jre
#WORKDIR app
#VOLUME /tmp
ARG JAR_FILE=./build/libs/*.jar
COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
ENTRYPOINT ["java","-jar","/app.jar"]
# ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/spring-boot-application.jar"]