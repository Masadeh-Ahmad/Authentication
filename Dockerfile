# Use a Java runtime as the base image
FROM openjdk:11-jre-slim

# Download and install GlassFish
RUN apt-get update && apt-get install -y wget unzip && \
    wget https://download.eclipse.org/ee4j/glassfish/glassfish-6.2.0.zip && \
    unzip glassfish-6.2.0.zip -d /opt && \
    rm glassfish-6.2.0.zip

# Copy the war file to the container
COPY target/Authentication.war /opt/glassfish6/glassfish/domains/domain1/autodeploy/
EXPOSE 8081
# Start GlassFish when the container starts
CMD ["/opt/glassfish6/glassfish/bin/asadmin", "start-domain", "--verbose", "domain1"]