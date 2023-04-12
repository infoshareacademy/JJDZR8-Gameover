FROM amazoncorretto:19

WORKDIR /opt/myapp/

COPY myCryptoWallet-WebApp/target/myCryptoWallet-WebApp-0.0.1-SNAPSHOT.jar .
#COPY myCryptoWallet-ConsoleApp/target/classes /opt/files
#COPY myCryptoWallet-ConsoleApp/target/myCryptoWallet-ConsoleApp-1.0-SNAPSHOT.jar .

EXPOSE 8080
EXPOSE 433
EXPOSE 9433

ENV FILE_PATH=opt/files

CMD java -jar myCryptoWallet-WebApp-0.0.1-SNAPSHOT.jar
