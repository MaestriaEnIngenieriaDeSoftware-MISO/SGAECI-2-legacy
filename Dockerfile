# Usa Maven para construir y Tomcat para servir la app
FROM maven:3.8.8-eclipse-temurin-8 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM tomcat:7-jre8
LABEL maintainer="tu-email@ejemplo.com"
# Elimina la app por defecto de Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*
# Copia el WAR generado y lo renombra como PDSW.war para contexto /PDSW
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/PDSW.war
# Expone el puerto por defecto de Tomcat
EXPOSE 8080
# Tomcat arranca automáticamente
