echo "Packaging for AWS ..."

aws cloudformation package --template-file sam-native.yaml \
                             --output-template-file packaged-sam.yaml \
                             --s3-bucket <s3-bucket-name>