# Maven
FROM maven AS maven_build
COPY . .
RUN mvn clean package -DskipTests

# Eclipse Temurin
FROM eclipse-temurin
COPY --from=maven_build /target/server-manager-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080

# Database Credentials
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://<host>:<port>/<database>
ENV SPRING_DATASOURCE_USERNAME=<username>
ENV SPRING_DATASOURCE_PASSWORD=<password>

# Database Configuration
ENV SPRING_DATASOURCE_DRIVER_CLASS_NAME=org.postgresql.Driver
ENV SPRING_JPA_PROPERTIES_HIBERNATE_DIALECT=org.hibernate.dialect.PostgreSQLDialect
ENV SPRING_JPA_PROPERTIES_HIBERNATE_FORMAT_SQL=true
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update

# Optional secrets for the API
ENV SPRING_CONFIG_IMPORT=optional:secrets.properties

ENTRYPOINT ["java","-jar","app.jar"]
