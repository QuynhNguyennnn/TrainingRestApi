FROM maven:3.8.3-openjdk-17

WORKDIR /api-0.0.1
COPY . .
RUN mvn clean install

CMD mvn spring-boot:run
