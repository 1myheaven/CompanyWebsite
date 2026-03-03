# Step 1: Build the application
FROM maven:3.9.6-amazoncorretto-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Step 2: Run the application
FROM amazoncorretto:17-alpine
WORKDIR /app
# *.jar use karne se version naming ka issue nahi aayega
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]