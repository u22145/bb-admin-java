#!/bin/bash

set -ex

if [ "$#" -eq 0 ]; then
    echo "Usage: ${0} [Image Name]"
    exit 0;
fi

cd $WORKSPACE/src/main/resources/public/vue
npm install
npm run build:${ENV}
cd $WORKSPACE
docker run --rm -v "$HOST_PATH":/app -w /app maven:3-openjdk-11 mvn clean package
cp target/bb-admin-0.0.1-SNAPSHOT.jar docker/bb-admin.jar
cd $WORKSPACE/docker

docker build -t $1 .