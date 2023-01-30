FROM openjdk:8

LABEL AUTHOR=GeorgeChan
LABEL MAIL=2495842975@qq.com

ENV LANG=C.UTF-8 \
    LC_ALL=C.UTF-8

EXPOSE 8089
WORKDIR /ROOT
ADD MyCardSystemBackstage-0.0.1-SNAPSHOT.jar /ROOT/app.jar
ENTRYPOINT ["java", "-jar"]
CMD ["app.jar"]



# 打成 war 包，则将上面的 jar 包代码注释掉，下方代码取消注释
#FROM tomcat:8.5.73
#EXPOSE 8089
#ADD target/bx-2.4.0.war /usr/local/tomcat/webapps/app.war