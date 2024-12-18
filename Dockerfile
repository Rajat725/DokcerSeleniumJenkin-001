FROM bellsoft/liberica-openjdk-alpine:17
RUN apk add curl jq
WORKDIR /home/Automation
ADD target/docker-package .
ADD runner.sh .
RUN dos2unix runner.sh
ENTRYPOINT sh runner.sh