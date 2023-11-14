# Utilisez une image de base Java
FROM openjdk:22-slim-bullseye

RUN apt-get update && apt-get install -y findutils

COPY . /app

WORKDIR /app

RUN ./gradlew build

EXPOSE 4000

CMD ["./gradlew", "run"]
