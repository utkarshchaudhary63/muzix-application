#need openjdk
FROM openjdk

#work directory
WORKDIR usr/lib

#jars
ADD ./target/GatewayAPI-0.0.1-SNAPSHOT.jar /usr/lib/GatewayAPI-0.0.1-SNAPSHOT.jar

#add starting jars when conatinerstart
ENTRYPOINT ["java","-jar","GatewayAPI-0.0.1-SNAPSHOT.jar"]