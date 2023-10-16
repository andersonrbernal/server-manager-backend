# Maven
FROM maven AS maven_build
COPY . .
RUN mvn clean package -DskipTests

# Eclipse Temurin
FROM eclipse-temurin
COPY --from=maven_build /target/server-manager-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080

# Default arguments
ARG database_driver=org.postgresql.Driver
ARG hibernate_dialect=org.hibernate.dialect.PostgreSQLDialect
ARG hibernate_format_sql=true
ARG hibermate_dll_auto=update
ARG optional_secrets_import=optional:secrets.properties

# Database Credentials
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://<host>:<port>/<database>
ENV SPRING_DATASOURCE_USERNAME=<username>
ENV SPRING_DATASOURCE_PASSWORD=<password>

# Database Configuration
ENV SPRING_DATASOURCE_DRIVER_CLASS_NAME=${database_driver}
ENV SPRING_JPA_PROPERTIES_HIBERNATE_DIALECT=${hibernate_dialect}
ENV SPRING_JPA_PROPERTIES_HIBERNATE_FORMAT_SQL=${hibernate_format_sql}
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=${hibermate_dll_auto}

# Optional secrets for the API
ENV SPRING_CONFIG_IMPORT=${optional_secrets_import}

ENTRYPOINT ["java","-jar","app.jar"]
