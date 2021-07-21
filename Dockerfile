FROM openjdk:11
ADD /build/libs/SpringRestProject-0.0.1-SNAPSHOT.jar SpringRestProject-0.0.1-SNAPSHOT.jar
EXPOSE 8086
ENTRYPOINT ["java","-jar","SpringRestProject-0.0.1-SNAPSHOT.jar"]