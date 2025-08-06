#!/bin/bash

export EXPERIMENT_TAG=$1

# Criação da imagem docker
docker image rm "ultimoguia:$EXPERIMENT_TAG"
docker build -t "ultimoguia:$EXPERIMENT_TAG" .
docker save "ultimoguia:$EXPERIMENT_TAG" | sudo k3s ctr images import -
docker image rm "ultimoguia:$EXPERIMENT_TAG"

# Deploy
envsubst < manifest-app.yaml | kubectl apply -f -
kubectl wait --for=condition=ready pod -l app=backend --timeout=60s

# Teste de carga
sleep 5
for ((i=1; i<=10; i++))
do
    sleep 5
    if [ $i -eq 10 ]; then
      # shellcheck disable=SC2086
      k6 run --summary-export="./experiments/report-$EXPERIMENT_TAG.json" performance-test.js
    else
      k6 run performance-test.js
    fi
done

# Desligamento e coleta de dados
envsubst < manifest-app.yaml | kubectl delete -f -
sleep 5

sudo k3s ctr images rm "docker.io/library/ultimoguia:$EXPERIMENT_TAG"
sudo mv /tmp/jfr-recordings/app.jfr "./experiments/app-$EXPERIMENT_TAG.jfr"