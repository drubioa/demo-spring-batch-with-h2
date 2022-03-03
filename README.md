# Demo of Spring Batch with h2
[![Java CI with Maven](https://github.com/drubioa/demo-karate-springboot/actions/workflows/maven.yml/badge.svg)](https://github.com/drubioa/demo-karate-springboot/actions/workflows/maven.yml)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

![logo](docs/logo.png)

The purpose of this project is make a example of how make a Spring Batch project with h2 database.

# Starting 🚀
First step is clone this repository from github to your local machine.

# Requirements 📋
To run in yout machine you should be installed next requirements:

1. If you like test this project in a docker container you should be installed Docker. The Dockerfile creates two containers, first a container with Maven whitch compile the project and generate *jar* file. Next this jar file going to the second container, a open-jdk-11 whitch containers run the application.

Run the following command to generate a container in the project folder

```bash
docker -t demo-spring-batch-with-h2 .
```

2. On the other hand, if you prefer compile and run in your local machine you may be installed Java in version 8 and Maven.

Run the following command to generate a container in the project folder

```bash
mvn package -f pom.xml
```
# Installation 🔧

To run this project with maven do the following command:

```bash
mvn spring-boot:run
```

On the other side, to deploy docker container:

```bash
docker run -p 8080:8080 demo-spring-batch-with-h2 -d
```
