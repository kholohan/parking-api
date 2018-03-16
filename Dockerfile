FROM java:8
WORKDIR /
COPY build/distributions/parking-api-1.0-SNAPSHOT.zip parking-api-1.0-SNAPSHOT.zip
RUN unzip parking-api-1.0-SNAPSHOT.zip && rm parking-api-1.0-SNAPSHOT.zip
ADD build/distributions/parking-api-1.0-SNAPSHOT/ parking-api-1.0-SNAPSHOT/
EXPOSE 8080
CMD ./parking-api-1.0-SNAPSHOT/bin/parking-api


