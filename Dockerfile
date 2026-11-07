# Fase 1: Compilación con Maven y Java 17
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copiar pom.xml y precargar dependencias en caché
COPY pom.xml .
RUN mvn dependency:go-offline -B || true

# Copiar código fuente y compilar JAR
COPY src ./src
RUN mvn clean package -DskipTests

# Fase 2: Imagen ligera para ejecución (JRE 17)
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# Copiar el JAR generado desde la fase de build
COPY --from=build /app/target/*.jar app.jar

# Soporte para puerto dinámico (Render, Railway, etc.)
ENV PORT=8080
EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java -Dserver.port=${PORT} -jar app.jar"]

