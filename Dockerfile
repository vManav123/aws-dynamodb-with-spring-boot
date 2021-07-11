FROM tomcat:latest
RUN rm -rf /usr/local/tomcat/webapps/*
COPY aws-dynamodb.war /usr/local/tomcat/webapps/ROOT.war
CMD ["catalina.sh","run"]