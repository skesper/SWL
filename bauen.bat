@echo off

call git pull

call mvn clean install

copy target\SWL.war C:\Java\apache-tomcat-...\webapps
