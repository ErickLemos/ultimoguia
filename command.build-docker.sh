#!/bin/bash

docker build -t ultimoguia:cds .
docker save ultimoguia:cds | sudo k3s ctr images import -
