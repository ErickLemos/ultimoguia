#!/bin/bash

docker build -t ultimoguia:cds .
cat withoutCds.Dockerfile | docker buildx build -t ultimoguia:withoutCds -f - .

docker save ultimoguia:cds | sudo k3s ctr images import -
docker save ultimoguia:withoutCds | sudo k3s ctr images import -
