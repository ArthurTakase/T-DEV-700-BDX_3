# Utilisez une image de base Java
FROM openjdk:11-jdk

COPY . /app

WORKDIR /app

RUN ./gradlew build

EXPOSE 4000

CMD ["./gradlew", "run"]
