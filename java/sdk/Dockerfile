FROM maven:3.9.0-eclipse-temurin as build
WORKDIR /usr/src/app
COPY . /usr/src/app

RUN mvn -f /usr/src/app/pom.xml clean package -DskipTests

FROM eclipse-temurin:17.0.6_10-jdk-jammy
WORKDIR /usr/src/app

COPY --from=build /usr/src/app/target/dapr-0.0.1-SNAPSHOT.jar dapr-0.0.1-SNAPSHOT.jar
COPY --from=build /usr/src/app/init_container.sh init_container.sh 

EXPOSE 8080 

ENTRYPOINT [ "/usr/src/app/init_container.sh" ]