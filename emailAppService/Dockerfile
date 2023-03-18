#need openjdk
FROM openjdk

#work directory
WORKDIR usr/lib

#jars
ADD ./target/emailAppService-0.0.1-SNAPSHOT.jar /usr/lib/emailAppService-0.0.1-SNAPSHOT.jar

#add starting jars when conatinerstart
ENTRYPOINT ["java","-jar","emailAppService-0.0.1-SNAPSHOT.jar"]