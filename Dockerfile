# Użyj obrazu Javy z Mavenem
FROM maven:3.8.8-eclipse-temurin-17 AS build

# Ustaw katalog roboczy
WORKDIR /app

# Skopiuj pliki projektu do obrazu
COPY . .

# Uruchom budowanie projektu
RUN ./mvnw package

# Drugi etap: Obraz z JDK
FROM eclipse-temurin:17-jdk

# Ustaw katalog roboczy dla aplikacji
WORKDIR /app

# Skopiuj skompilowany plik JAR z poprzedniego etapu
COPY --from=build /app/target/*.jar app.jar

# Ustaw komendę startową aplikacji
ENTRYPOINT ["java", "-jar", "app.jar"]
