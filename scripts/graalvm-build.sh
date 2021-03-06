#!/bin/sh
# echo "Startin GraalVM build"
echo "called"
# -H:+PrintAnalysisCallTree -H:Log=registerResource
/usr/lib/graalvm/bin/native-image  -H:+TraceClassInitialization --initialize-at-build-time=reactor.core.publisher.Mono --initialize-at-build-time=reactor.core.publisher.Flux --no-fallback --no-server -cp /func/build/libs/emp-data-0.1-all.jar
rm -rf /func/native-image/*

chmod 755 /func/server
mv /func/server /func/native-image/server
cp /func/scripts/bootstrap /func/native-image/bootstrap
cd /func/native-image && zip -j function.zip bootstrap server 