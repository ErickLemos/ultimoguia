#!/bin/bash
export EXPERIMENT_TAG=$1

# Deploy
docker compose up -d
sleep 5

. ./run-loadtest.sh "$EXPERIMENT_TAG"

# Desligamento e coleta de dados
sleep 5
docker compose down
docker image rm ultimoguia-app:latest
sudo chmod +777 ./experiments/app.jfr
sudo mv ./experiments/app.jfr "./experiments/app-$EXPERIMENT_TAG.jfr"