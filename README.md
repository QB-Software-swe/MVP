<!-- LTeX: language=it -->
# PoC - QB Software
## Per eseguire l'applicazione dentro il container
```bash
$ docker compose up --build
```

## Per fare la build del poc ed eseguirlo senza container
```bash
$ mvn clean package
$ java -jar target/poc_jmap-0.1-SNAPSHOT-shaded.jar
```
## Configurazione server
- Porta: 9999

Endpoint:
- /.well-known/jmap
- /api
- /download
- /upload

## Collegarsi al server
Utilizzare un client JMAP che permetta di collegarsi utilizzando HTTP (non HTTPS).

URL server:
```
http://<server_ip>:9999/.well-known/jmap
```

# Scelte tecnologiche
- Docker
- Java 21 LTS
- MongoDB (sync)
- Jetty
- Maven

# TODO
- [ ] mettere *final* gli argomenti di tutte le funzioni call degli *usecase*.
- [ ] rinominare update e create dei Foo/Set

# Check
- Nessun controllo viene effettivamente fatto se la risposta supera i limiti che il server aveva indicato all'inizio della conversazione.