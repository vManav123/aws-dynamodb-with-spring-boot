# Base RA Docker Image for Tomcat8-JRE8
FROM adoptopenjdk/openjdk11:alpine:jre

MAINTAINER "Manav Verma <manav.verma.-nd@disney.com>"

LABEL DESCRIPTION="Docker Image With Tomcat9 and Java11 runtime for GDM Service"
ENV LOGDIR "/usr/local/tomcat/logs"

# add packages needed by docker container
RUN apt-get update && apt-get install -y --no-install-recommends \
    curl -y \
    net-tools -y \
    && rm -fr /var/lib/apt/lists/*

#RUN apt-get update && apt-get install -y vim

RUN mkdir -p /scripts /props/wdpr /appdynamics

# configure app specific tomcat settings here
# NOTE be careful not to unintentionally overwrite tomcat configurations from the RA base image
ADD ./scripts/app.sh /scripts/
ADD ./scripts/update_cacerts.sh /scripts/
ADD ./scripts/setenv.sh /usr/local/tomcat/bin/
RUN chmod +x /usr/local/tomcat/bin/setenv.sh /scripts/app.sh /scripts/update_cacerts.sh
RUN /scripts/update_cacerts.sh

# Copy cpc-licpubapps-srp-core WAR into webapps
ADD cpc-licpubapps-srp-core-0.0.1-SNAPSHOT.war $CATALINA_HOME/webapps/ROOT.war

# Run scripts in base image to startup appD and catalina with the appropriate settings
CMD ["/scripts/run.sh"]