# Utiliser une image de base avec JDK 17
FROM openjdk:22-jdk-bullseye

# Installer les dépendances nécessaires pour télécharger et décompresser le SDK Android

# Définir les variables d'environnement pour le SDK Android
ENV ANDROID_HOME=/usr/local/android-sdk
ENV PATH=$PATH:$ANDROID_HOME/cmdline-tools/latest/bin:$ANDROID_HOME/platform-tools

# Télécharger et décompresser le SDK Android
RUN mkdir -p $ANDROID_HOME/cmdline-tools \
    && wget https://dl.google.com/android/repository/commandlinetools-linux-8512546_latest.zip -O cmdline-tools.zip \
    && unzip cmdline-tools.zip -d $ANDROID_HOME/cmdline-tools \
    && mv $ANDROID_HOME/cmdline-tools/cmdline-tools $ANDROID_HOME/cmdline-tools/latest \
    && rm cmdline-tools.zip

# Accepter les licences du SDK Android et installer les composants nécessaires
RUN yes | sdkmanager --licenses \
    && sdkmanager "platform-tools" "platforms;android-31"

# Copier le projet dans le conteneur
COPY . /app

# Définir le répertoire de travail
WORKDIR /app

# Donner les droits d'exécution au script Gradle
RUN chmod +x ./gradlew

# Construire l'application
RUN ./gradlew build