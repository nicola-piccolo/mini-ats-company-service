#!/bin/bash
current_directory=$(pwd)
./mvnw package && \
	mkdir -p target/dependency; cd target/dependency && \
	jar -xf ../*.jar && \
	cd $current_directory && \
	docker build --tag miniats/company-service --file src/main/resources/static/docker/Dockerfile .