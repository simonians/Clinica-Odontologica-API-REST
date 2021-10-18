# Proyecto Clínica Odontológica: Proyecto Integrador BackEnd I

El proyecto consiste en una API REST de una clínica odontológica. El sistema permite administrar la reserva de turnos para la clínica. En el mismo se permite: 
- Administrrar datos de odontólgos y pacientes.
- Validar el ingreso al sistema mediante un login con usuario y password. 
- Asignar a un paciente un turno con un respectivo odontólogo a una determinada fecha y horario.
- Mostrar una grilla de turnos de la semana próxima. 

## Requerimientos
1) Java 11
2) Maven

## Instalación y correr el proyecto
Java puede instalarse según el sistema operativo en el siguiente link [Instalacion Java 11](https://www.oracle.com/java/technologies/downloads/#java11) y maven a través de [Instalacion Maven](https://maven.apache.org/download.cgi).

## Primer uso
1) Clonar este repositorio. Para esto, correr el siguiente comando en la teminal de tu computadora:
```
git clone https://github.com/simonians/Clinica-Odontologica-API-REST.git
```
2) Deberías tener esta estructura de carpetas
```
Clinica-Odontologica-API-REST
│   README.md
│   clinica_dental.log
│   mvnw
│   mvnw.cmd
│   pom.xml
|   log4j.properties
└───src
```
3) Para correr el proyecto deben ejecutarse los siguientes comandos en la terminal (posicionándose en la carpeta del proyecto): 
```
cd Clinica-Odontologica-API-REST
mvn clean package
```
4) Luego de correr el comando `mvn clean package` la estructura de carpetas debería ser:
```
Clinica-Odontologica-API-REST
│   README.md
│   clinica_dental.log
│   mvnw
│   mvnw.cmd
│   pom.xml
|   log4j.properties
└───src
└───target
```
5) Ahora, correr el siguiente comando: 
```
cd target
java -jar ClinicaOdontologica.jar
```
6) Con esto, el proyecto debería estar corriendo en el puerto configurado por defecto (el puerto 8082). Si se quisiera modificar el puerto debería cambiarse la propiedad server.port=8082 por el puerto donde desee correrse el proyecto. Esto debe hacerse en `/src/resources/application.properties`

7) Una vez que la aplicación esté corriendo, puede accederse a la url [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) y ver la documentación. 
