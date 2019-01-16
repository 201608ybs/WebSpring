FROM openjdk:8-jre
RUN mkdir /app
COPY ./target/springtest-0.0.1-SNAPSHOT.jar /app/springtest-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD java -jar /app/flawsweeper.jar