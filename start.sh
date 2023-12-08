#!/bin/bash
docker-compose -f docker-compose-db.yml -p abc up -d
sleep 10

#docker exec -i spring_db psql -U localdb -d localdb < src/main/resources/schema.sql
#docker exec -i spring_db psql -U localdb -d localdb < src/main/resources/data.sql

sed -i 's|spring.datasource.url=.*|spring.datasource.url=jdbc:postgresql://localhost:5430/localdb|' src/main/resources/application.properties
sed -i 's|spring.datasource.username=.*|spring.datasource.username=localdb|' src/main/resources/application.properties
sed -i 's|spring.datasource.password=.*|spring.datasource.password=localdb|' src/main/resources/application.properties

#./mvnw clean install -D skipTests
#java -jar target/spring-labs-0.0.1-SNAPSHOT.jar
