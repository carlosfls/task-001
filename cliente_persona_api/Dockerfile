FROM openjdk:21
VOLUME /tmp
EXPOSE 8081
ADD ./build/libs/cliente_persona_api-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]