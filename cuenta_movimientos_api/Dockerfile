FROM openjdk:21
VOLUME /tmp
EXPOSE 8080
ADD ./build/libs/cuenta_movimientos_api-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]