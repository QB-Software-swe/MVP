<!-- LTeX: language=it -->
# Grafite + Jetty
## Eseguire grafite
```bash
$ mvn clean package
$ java -jar target/Grafite-0.1-SNAPSHOT-shaded.jar
```

Porta: 9999

## Client e-mail
Usare ltt.rs di iNPUTmice, vi server Android Studio oppure fate direttamente con Gradle ma è abbastanza una pazzia. Per Android Studio vi consiglio, se usate l'emulatore, di non andare su una versione troppo recente di Android, pesano molto in termini di risorse!

Per il [client e-mail](https://github.com/QB-Software-swe/Ltt-rs-android-patch) usare la versione con la patch che disabilità il controllo HTTPS. Quando emulate l'applicazione *DOVETE* farla partire in modalità *DEBUG* e modificare il file network_security_config.xml in res/xml, sostituendo l'indirizzo IP con quello del server, in caso di dubbi chiedere.