#!/bin/bash

export EXPERIMENT_TAG=$1

# Deploy
docker compose up -d
sleep 5

# Teste de carga
for ((i=1; i<=10; i++))
do
    sleep 5
    if [ $i -eq 10 ]; then
      k6 run --summary-export="./experiments/report-$EXPERIMENT_TAG.json" performance-test.js
    else
      k6 run performance-test.js
    fi
done

# Desligamento e coleta de dados
sleep 5
docker compose down
docker image rm ultimoguia-app:latest
sudo chmod +777 ./experiments/app.jfr
sudo mv ./experiments/app.jfr "./experiments/app-$EXPERIMENT_TAG.jfr"