#!/bin/bash
if [ "${1,,}" != 'up' ] && [ "${1,,}" != 'down' ] 
then
    echo "Usage:  company-service-compose COMMAND
    
    COMMAND:
        up      bootstrap the whole cluster
        down    tear down the cluster"
    exit 1
fi
if [ "${1,,}" == 'up' ]
then
    exec docker-compose --file ./src/main/resources/static/docker/docker-compose.yml up --detach
    exit 0
fi
if [ "${1,,}" == 'down' ]
then
    exec docker-compose --file ./src/main/resources/static/docker/docker-compose.yml down
    exit 0
fi