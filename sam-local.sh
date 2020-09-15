#!/bin/sh

./docker-build.sh
sam local start-api -t sam-native.yaml --log-file logs.txt --debug