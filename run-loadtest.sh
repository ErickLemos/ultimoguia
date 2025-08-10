#!/bin/bash

for ((i=1; i<=10; i++))
do
  sleep 5
    if [ $i -eq 10 ]; then
      k6 run --summary-export="./experiments/report-$1.json" performance-test.js
    else
      k6 run performance-test.js
    fi
done


