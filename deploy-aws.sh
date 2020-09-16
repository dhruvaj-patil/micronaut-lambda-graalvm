echo "Deploynig to AWS"

aws cloudformation deploy \
--template-file ./packaged-sam.yaml \
--stack-name <any-stack-name> \
--capabilities CAPABILITY_IAM
