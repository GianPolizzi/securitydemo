# Spring Security Demo Application

Basic Back-end start architecture for simple web app in **Java EE (Jakarta)** with **Spring Framework** and **Maven**, built with **InstelliJ IDE**.
The application show the **Springboot** configuration, integrated with **Spring Data** and **Spring Security** modules.
The interface is realized with **Swagger-UI** library.
This starter securitydemo App focuses attentiontion about the following security operations:

- *Security Authentication*
- *JWT (JSON Web Token)*
- *Roles Autorization & Permission*


## Specifications

- Language: Java 23
- Framework: Spring (Springboot 3.4.2)
- ORM: Hibernate 6.5.2.Final
- Database: MySQL 8.0.33


### APIs
1. localhost:8080/authorization/welcome
2. localhost:8080/authorization/register
3. localhost:8080/authorization/authenticate
4. localhost:8080/admin/welcome
5. localhost:8080/user/welcome


## How to start

### 1. Clone repository
Clone repository in your device using git command:
  git clone

### 2. Open project
Open securitydemo project with IntelliJ IDE (or others)

### 3. Maven build automation
Check pom.xml file and use maven command to build the project using:
  mvn clean install

### 4. Prepare MySQL DB
- Install MySQL in your device (version 8 ->)
- Create a new schema named *securitydb*
- Create the table User with query:
  ``` sql
    CREATE TABLE `user` (
      `id` int NOT NULL AUTO_INCREMENT,
      `username` varchar(50) DEFAULT NULL,
      `password` varchar(20) DEFAULT NULL,
      `role` varchar(10) DEFAULT 'USER',
      PRIMARY KEY (`id`)
    );
  ```

### 5. Start project
Run securitydemo project and navigate with your browser to URL: 
  http://localhost:8080/swagger-ui/index.html


##### 6. Now let's try application
Please use API 2 to register a new User:
- Set definition Authorization from scroll down Swagger-UI menù (top/right) and open API 2;
- It's not necessary set the id so you can remove key and value from request;
- Set Role ADMIN for the first user and the Role USER for the second (if you set role: null, USER role will be set by default).

Please login using API 3:
- Copy the token
- Change definition going to Admin or User
- Paste the token and Authorize with button
- Now you can try send request for this ROLE


### Note & reference

- Documentation: https://www.baeldung.com/java-spring-security-permit-swagger-ui
- Documentation: https://www.baeldung.com/spring-security-sign-jwt-token
- Spring security JWT authentication: https://www.youtube.com/watch?v=HYBRBkYtpeo
- JWT IO: https://jwt.io/
