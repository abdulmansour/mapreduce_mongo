# mapreduce_mongo

## Run Mongo in Docker
open new terminal
cd into mapreduce_mongo/Docker
run command: docker-compose up

## Run Springboot
run spring boot application on ide
on first launch will populate mongodb

## Populate Mongo with data
open new terminal
ensure docker is running on operating system
run command: docker exec -it mongo bash
run command: mongo admin -u root -p 'root'
run command: use test
run command: db.metric.stats().count
confirm that size should be: 28422

to delete all collection metrics run command: db.metric.remove({})
