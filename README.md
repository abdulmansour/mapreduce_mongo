# mapreduce_mongo

### Run mongo in docker container
- open new terminal
- cd into mapreduce_mongo/Docker
- run command: docker-compose up

### Run springboot
- run spring boot in ide
- on first launch will populate mongodb

### Populate mongo with data
- open new terminal
- ensure docker is running on operating system
- run command: docker exec -it mongo bash
- run command: mongo admin -u root -p 'root'
- run command: use test
- run command: db.metric.stats().count
- confirm that size should be: 28422
- to delete all collection metrics run command: db.metric.remove({})

### Run MapReduce using Postman
- open postman
- execute GET request: localhost:8102/mapreduce/
