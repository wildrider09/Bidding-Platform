FROM openjdk:8
COPY ./target/biddingPlatform-0.0.1-SNAPSHOT.jar ./
WORKDIR ./
CMD ["java", "-jar", "biddingPlatform-0.0.1-SNAPSHOT.jar"]
