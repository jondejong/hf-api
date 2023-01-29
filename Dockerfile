FROM azul/zulu-openjdk

ARG JAR_FILE=./app/build/libs/hfapi-all.jar

# cd /usr/local/runme
WORKDIR /usr/local/runme

# copy target/find-links.jar /usr/local/runme/app.jar
COPY ${JAR_FILE} app.jar

# java -jar /usr/local/runme/app.jar
ENTRYPOINT ["java","-jar","app.jar"]