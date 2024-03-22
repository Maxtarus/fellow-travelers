FROM maven:3.8.3-openjdk-17 AS BUILDER
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17
WORKDIR /app
COPY --from=builder /app/target/*.jar ./app.jar
CMD [ "java", "-jar", "/app/app.jar" ]