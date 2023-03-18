#need openjdk
FROM openjdk

#work directory
WORKDIR usr/lib

#jars
ADD ./target/FavMovieSer-0.0.1-SNAPSHOT.jar /usr/lib/FavMovieSer-0.0.1-SNAPSHOT.jar

#add starting jars when conatinerstart
ENTRYPOINT ["java","-jar","FavMovieSer-0.0.1-SNAPSHOT.jar"]