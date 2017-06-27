# Pull base image.
FROM centos:latest
MAINTAINER leophan<leo.phan0411@gmail.com>
# Arguments java version.
ARG JAVA_MAJOR_VERSION=8
ARG JAVA_UPDATE_VERSION=131
ARG JAVA_BUILD_NUMBER=11
# Set ENV java.
ENV JAVA_HOME /usr/java/jdk1.${JAVA_MAJOR_VERSION}.0_${JAVA_UPDATE_VERSION}
ENV PATH $PATH:$JAVA_HOME/bin
# Install java.
RUN curl -L -O -H "Cookie: gpw_e24=http://www.oracle.com/; oraclelicense=accept-securebackup-cookie" \
  "http://download.oracle.com/otn-pub/java/jdk/${JAVA_MAJOR_VERSION}u${JAVA_UPDATE_VERSION}-b${JAVA_BUILD_NUMBER}/d54c1d3a095b4ff2b6607d096fa80163/jdk-${JAVA_MAJOR_VERSION}u${JAVA_UPDATE_VERSION}-linux-x64.rpm" \
  && yum -y install jdk-8u131-linux-x64.rpm \
  && rm -rf jdk-8u131-linux-x64.rpm \
  && yum clean all && rm -rf /tmp/* /var/tmp/* && rm -rf /var/cache/*
# Check java.
RUN ["java","-version"]

WORKDIR /opt/src

# Arguments zookeeper version.
ARG ZOO_MAJOR=3
ARG ZOO_UPDATE=4
ARG ZOO_BUILD=6
ARG ZOO_PORT=2181
ARG ZOO_VERSION=${ZOO_MAJOR}.${ZOO_UPDATE}.${ZOO_BUILD}
# Set ENV zookeeper.
ENV ZOO_HOME /opt/src/zookeeper-${ZOO_VERSION}
ENV PATH $PATH:$ZOO_HOME/bin

# Install zookeeper.
RUN curl -L -O http://mirrors.viethosting.com/apache/zookeeper/zookeeper-${ZOO_VERSION}/zookeeper-${ZOO_VERSION}.tar.gz \
  && tar -xzf zookeeper-${ZOO_VERSION}.tar.gz \
  && rm -rf zookeeper-${ZOO_VERSION}.tar.gz
RUN mv /opt/src/zookeeper-${ZOO_VERSION}/conf/zoo_sample.cfg /opt/src/zookeeper-${ZOO_VERSION}/conf/zoo.cfg 

EXPOSE $ZOO_PORT

COPY ./docker-entrypoint.sh /
ENTRYPOINT ["/docker-entrypoint.sh"]