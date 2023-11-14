# Utilisez une image de base Java
FROM openjdk:latest

COPY . /app

WORKDIR /app

RUN ./gradlew build

EXPOSE 4000

CMD ["./gradlew", "run"]
