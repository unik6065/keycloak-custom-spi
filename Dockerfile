FROM maven:3.8.4-openjdk-17 AS build

WORKDIR /app

COPY . /app/

RUN mvn clean package

FROM quay.io/keycloak/keycloak:23.0.7

COPY --from=build app/target/keycloak-spi-kafka.jar /opt/keycloak/providers
