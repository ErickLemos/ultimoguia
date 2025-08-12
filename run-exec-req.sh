#!/bin/bash

ACCESS_TOKEN=$(curl -s -X POST "http://localhost:8081/realms/app/protocol/openid-connect/token" \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "client_id=app-public" \
  -d "grant_type=password" \
  -d "username=user" \
  -d "password=user" \
  -d "scope=openid profile email" | jq -r '.access_token')

curl --request POST \
  --url http://localhost:8080/notas \
  --header "Authorization: Bearer $ACCESS_TOKEN" \
  --header 'Content-Type: application/json' \
  --header 'User-Agent: insomnia/11.4.0' \
  --data '{
	"titulo": "teste",
	"conteudo": "conteudo"
}'

echo ""