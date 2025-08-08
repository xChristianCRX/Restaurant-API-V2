# Etapa de build
FROM maven:3.9.6-eclipse-temurin-21 as builder

WORKDIR /app
COPY . .

RUN mvn clean package -DskipTests

# Etapa de execução
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]