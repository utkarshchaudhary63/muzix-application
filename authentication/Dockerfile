#need openjdk
FROM openjdk

#work directory
WORKDIR usr/lib

#jars
ADD ./target/authentication-0.0.1-SNAPSHOT.jar /usr/lib/authentication-0.0.1-SNAPSHOT.jar

#add starting jars when conatinerstart
ENTRYPOINT ["java","-jar","authentication-0.0.1-SNAPSHOT.jar"]