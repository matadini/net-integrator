# NET-INTEGRATOR

## Moduly

* **net-integrator-client-fx** - klient JavaFX do zarzadzania aplikacja serwerowa
* **net-integrator-server** - aplikacja serwerowa sluzaca jako integrator miedzy systemami (przekierowania/transformacje danych)
* **net-integrator-fake-server** - aplikacja serwerowa sluzaca za atrape systemu trzeciego ktora otrzymuje przekierowane requesty, 
powstała na potrzeby demostracji przekierowan


## build and run: wszystko

// TODO: ogarnac

## build and run: net-integrator-server

* sklonuj repo
* przejdzi do kataolgu `net-integrator-server`
* wywołaj z konsoli: `mvn clean install`
* przejdz do katalogu `net-integrator-server/target`
* uruchom aplikacje w konsoli `java -jar <nazwa_jara, np. net-integrator-server-1.0-snapshot.jar>`
* uruchom przegladarke i sprawdz: `localhost:8080/test`
* powinno byc git ;)

## build and run: net-integrator-fake-server

* sklonuj repo
* przejdzi do kataolgu `net-integrator-fake-server`
* wywołaj z konsoli: `mvn clean install`
* przejdz do katalogu `net-integrator-fake-server/target`
uruchom aplikacje w konsoli `java -jar 
<java -jar net-integrator-fake-server-1.0-SNAPSHOT-jar-with-dependencies.jar>`
* uruchom przegladarke i sprawdz: `localhost:9090/test`
* powinno byc git ;)
