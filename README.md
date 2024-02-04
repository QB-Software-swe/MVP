<!-- LTeX: language=it -->
# PoC - QB Software
## Eseguire il PoC
```bash
$ mvn clean package
$ java -jar target/poc_jmap-0.1-SNAPSHOT-shaded.jar
```
## Configurazione server
Porta: 9999

## Collegarsi al server
Per collegarsi al server usare un client JMAP che permetta di collegarsi utilizzando HTTP (non HTTPS).

URL server:
```
http://<server_ip>:9999/.well-known/jmap/
```