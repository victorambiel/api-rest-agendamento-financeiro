#!/bin/bash

./mvnw clean
./mvnw install -DskipTests
sudo docker build -t victorambiel/agendamento .
sudo docker run -p 8080:8080 victorambiel/agendamento