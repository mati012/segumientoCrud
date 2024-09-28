FROM openjdk:22-ea-24-oracle

RUN apt-get update &&  apt-get install -y maven

WORKDIR /app

COPY pom.xml . 
COPY src /app/src
COPY Wallet_N72BZHZWYZGTE7OH /app/wallet

ENV TNS_ADMIN=/app/wallet 

RUN mvn clean package

FROM openjdk:22-ea-24-oracle

COPY --from=buildstage /app/target/seguimiento-0.0.1-SNAPSHOT.jar /app/seguimiento.jar

COPY Wallet_N72BZHZWYZGTE7OH /app/wallet
ENTRYPOINT [ "java", "-jar","/app/seguimiento.jar" ]