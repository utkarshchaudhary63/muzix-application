#need openjdk
FROM openjdk

#work directory
WORKDIR usr/lib

#jars
ADD ./target/EurekaService-0.0.1-SNAPSHOT.jar /usr/lib/EurekaService-0.0.1-SNAPSHOT.jar

#add starting jars when conatinerstart
ENTRYPOINT ["java","-jar","EurekaService-0.0.1-SNAPSHOT.jar"]