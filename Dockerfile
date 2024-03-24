# Используем базовый образ с Java 11
FROM adoptopenjdk/openjdk11:alpine-slim

# Устанавливаем рабочую директорию внутри контейнера
WORKDIR /app

# Копируем JAR-файл в контейнер
COPY target/vacation-salary-calculator-1.0.0.jar /app/vacation-salary-calculator-1.0.0.jar

# Команда для запуска Spring Boot приложения при старте контейнера
CMD ["java", "-jar", "vacation-salary-calculator-1.0.0.jar"]
