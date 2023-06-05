FROM eclipse-temurin:17-jdk-jammy as builder
#FROM maven:3.8.3-openjdk-11-slim as builder
WORKDIR /opt/app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
#COPY pom.xml ./
RUN chmod +x mvnw
#RUN ./mvnw dependency:purge-local-repository
#RUN ./mvnw dependency:go-offline
COPY . .
RUN ./mvnw clean install -Dskiptests
#RUN mvn clean -e -B package

FROM eclipse-temurin:17-jre-jammy
WORKDIR /opt/app
EXPOSE 8080
COPY --from=builder /opt/app/metabucket-application/target/*.jar /opt/app/*.jar
ENTRYPOINT ["java", "-jar", "/opt/app/*.jar" ]