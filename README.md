# NET-INTEGRATOR

## Moduly

* **net-integrator-client-fx** - klient JavaFX do zarzadzania aplikacja serwerowa
* **net-integrator-server** - aplikacja serwerowa sluzaca jako integrator miedzy systemami (przekierowania/transformacje danych)
* **net-integrator-fake-server** - aplikacja serwerowa sluzaca za atrabe systemu trzeciego ktora otrzymuje przekierowane requesty

## build and run: net-integrator-server

* sklonuj repo
* przejdzi do kataolgu `net-integrator-server`
* wywołaj z konsoli: `mvn clean install`
* przejdz do katalogu `net-integrator-server/target`
* uruchom aplikacje w konsoli `java -jar <nazwa_jara, np. net-integrator-server-1.0-snapshot.jar>`
* uruchom przegladarke i sprawdz: `localhost:8080/test`
* powinno byc git ;)
