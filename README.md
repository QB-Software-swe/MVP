<!-- LTeX: language=it -->
[![CI](https://github.com/QB-Software-swe/MVP/actions/workflows/CI.yaml/badge.svg)](https://github.com/QB-Software-swe/MVP/actions/workflows/CI.yaml)
[![codecov](https://codecov.io/gh/QB-Software-swe/MVP/graph/badge.svg?token=8I095AUWIH)](https://codecov.io/gh/QB-Software-swe/MVP)

# Jmap Server - QB Software
MVP commissionato nel capitolato numero otto del progetto di Ingegneria del Software 2023/2024.

## Per eseguire l'applicazione dentro il container
```bash
$docker compose up --build
```

## Per fare la build del MVP ed eseguirlo senza container
```bash
$mvn clean package
$java -jar target/demo_jmap_server-1.0.0-jar-with-dependencies.jar
```
Per eseguire il server senza Docker bisogna avere comunque il database ed è necessario aggiornare la stringa di connessione (DB_CONNECTION_STRING) all'interno del file compose.yaml. 

## Configurazione server & database
- Porta del server: 9999
- Porta del database: 27017

Endpoint:
- /.well-known/jmap
- /api
- /download
- /upload
- /eventSource

## Collegarsi al server
Utilizzare un client JMAP che permetta di collegarsi utilizzando HTTP (non HTTPS).

URL server:
```
http://<server_ip>:9999/.well-known/jmap
```

# Scelte tecnologiche
- Docker
- Java 21 LTS
- iNPUTmice/jmap
- MongoDB (sync)
- Jetty
- Maven
- Guice
- Locust
- Postman
- TestContainers
