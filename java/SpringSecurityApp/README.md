# Project using the Spring Security module

## Command used to create the project from console

spring init -d=devtools,web,lombok,mysql,data-jpa,validation,security --type=maven-project --build=maven --java-version=21
--group-id=com.co.manuel --artifact-id=SpringSecurityApp --name=SpringSecurity --description="Using spring secuirity with mysql app" SpringSecurityApp

## Connection to the mysql data base in container, add to the network

Command Example: docker network connect hotel-network labs

Command Example for default user: curl -u "user" http://localhost:3000/auth/hello-secured

## Model used:

![Spring Security API model image](./SpringSecurity.png)
