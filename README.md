# NET-INTEGRATOR

## Moduly


* **net-integrator-desktop** - klient JavaFX do zarzadzania aplikacja serwerowa
* **net-integrator-client** - zestaw klientow do integracji miedzy aplikacja desktop-fx a glownym serwerem aplikacji 
* **net-integrator-fake-server** - aplikacja serwerowa sluzaca za atrape systemu trzeciego ktora otrzymuje przekierowane requesty, 
powstała na potrzeby demostracji przekierowan
* **net-integrator-server** - aplikacja serwerowa spinajaca wszystkie
moduly aplikacji
* **net-integrator-db-util** - modul bazodanowy dostarczajacy zaleznosci
dla JPA 
* **net-integrator-http-util** - modul dostarczajacy zaleznosci zwiazane
z HTTP, biblioteki klienckie, REST i tego typu stuff
* **net-integrator-loadbalancer** - atrapa na modulu zarzadzania
mozliwymi hostami gdzie mozna przekierowac request, niby ma implementowac
jakis mechanizm rozkladania ruchu
* **net-integrator-mapping** - modul ktory odpowiada za przechowywanie danych
o dostepnych mapowaniach
* **net-integrator-script** - modul dostarcza funkcjonalnosc zarzadzania
skryptami ktore sa podpiete pod mapowania http oraz funkcje wywolywania
w runtime, skryptow groovy


## build and run: konsola

* sklonuj repo
* przejdzi do kataolgu projektu np. `cd net-integrator`
* upewnij sie czy masz mavena dostepnego z konsoli `mvn -version`, jak nie to ogarnij
* wywołaj z konsoli: `mvn clean install -DskipTests=true`
* jezeli masz polaczenie z netem to powinno wszystko byc git
* uruchomienie dema
    * uruchom **net-integrator-fake-server**
        * przejdz do katalogu `net-integrator-fake-server/target`
        * uruchom aplikacje w konsoli `java -jar <nazwa_jara> np. java -jar net-integrator-fake-server-1.0-SNAPSHOT-jar-with-dependencies.jar`
        * uruchom przegladarke i sprawdz: `localhost:9090/test`
        * powinno byc git 
    * uruchom drugie okno konsoli aby nie ubijac okna z 
    procesem fake-servera, ewentualnie odpal fake-server
    jako proces w tle
    * uruchom **net-integrator-server**
        * przejdz do katalogu `net-integrator-server/target`
        * uruchom aplikacje w konsoli `java -jar <nazwa_jara> np. net-integrator-server-1.0-snapshot-jar-with-dependencies.jar>`
        * uruchom przegladarke i sprawdz: `localhost:8080/test`
        * powinno byc git 
    * Zainstaluj i uruchom postmana: https://www.getpostman.com/
        * zaimportuj do niego kolekcje z katologu 
        `net-integrator/resources/postman/Inzynierka...json`
        * przetestuj demo w postmanie
        
        
