# Etapa de construcción
FROM maven:3.9.5-eclipse-temurin-17 AS build

# Especificar que el código está en /wasiseguro
WORKDIR /app
COPY wasiseguro/pom.xml .
RUN mvn dependency:go-offline
COPY wasiseguro/src ./src
RUN mvn clean package -DskipTests

# Etapa de ejecución
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

EXPOSE 8080

HEALTHCHECK --interval=30s --timeout=3s --start-period=30s --retries=3 \
  CMD wget --quiet --tries=1 --spider http://localhost:8080/actuator/health || exit 1

# Agregar debug antes de ejecutar
RUN echo '#!/bin/sh' > /entrypoint.sh && \
    echo 'echo "=== DEBUG: Environment Variables ==="' >> /entrypoint.sh && \
    echo 'echo "DATABASE_URL: $DATABASE_URL"' >> /entrypoint.sh && \
    echo 'echo "SPRING_DATASOURCE_URL: $SPRING_DATASOURCE_URL"' >> /entrypoint.sh && \
    echo 'echo "====================================="' >> /entrypoint.sh && \
    echo 'exec java -jar app.jar' >> /entrypoint.sh && \
    chmod +x /entrypoint.sh

ENTRYPOINT ["/entrypoint.sh"]