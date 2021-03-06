# Operation Test

_Ejemplo de c谩lculo de operaciones sumas, restas, ...dado uno o varios operandos_


## Comandos Requeridos 馃搵

```
1. Construir/Empaquetar: gradle clean build -x
2. Dockerizar: gradle bootBuildImage
3. Probar: gradle test
4. Ejecutar contenedor: docker run -d -p 8080:8080 operators:0.0.1-SNAPSHOT
5. CURL probar API: http://localhost:8080/swagger-ui/

```
## Seguridad

Al ser un servicio de prueba se configura seguridad con autenticaci贸n b谩sica, 
para consultar el servicio, logicamente para un ambiente productivo ya se puede pensar en Oauth2, 
e integraci贸n con autenticaci贸n con servicios google, facebook, ldap, keycloak...etc.

para consultar los servicios puede usar el usuario: admin y el password: admin12345


## Arquitectura

![alt text](https://github.com/enavarrom/operatortest/blob/develop/DiagramClass.drawio.png?raw=true)

La soluci贸n planteada es la de un microservicio API REST construido con Java y el framework de Spring._
Esta soluci贸n permite realizar operaciones matem谩ticas b谩sicas como suma, restas, multiplicacion, divisi贸n y potenciaci贸n_
a traves de una sesi贸n, a la cual se le env铆an los operandos, y luego se realiza la operaci贸n.

La soluci贸n es sencilla dado que el problema en menci贸n es sencillo. Su acceso es mediante peticiones POST y GET a trav茅s del controlador 
**_OperationController_** el cual tiene los m茅todos para crear sesi贸n, agregar operandos a la sesi贸n y ejecutar la operaci贸n. Este controlador,
funciona como una fachada para el servicio **_OperationService_** que es el componente que tiene la l贸gica de negocio apoyado de la clase 
**_OperationSession_** quien es la que se encarga de habilitar el objeto que representa la sesi贸n en la cual se realizar谩n las operaciones.

Una vez creada la sesi贸n, **_OperationSession_** contiene el m茅todo executeOperation el cual usa la invocaci贸n de la operaci贸n mediante una 
interfaz funcional de tipo **_ExecuteOperation_** el cu谩l representa la operaci贸n a realizar.


## Otros Aspectos de la implementaci贸n

Adicional para efectos de la prueba se adicionan escritura de logs usando Sl4j, 
se deja la configuraci贸n de logs por defecto en consola, aunque se podr铆a configurar 
y es mejor usar archivos de texto o inclusive centralizar los logs.

Se deja una configuraci贸n base y b谩sica para la trazabilidad de las peticiones realizadas mediante Spring sleuth, 
el cual podr铆a integrarse con Zipking. O buscar una implementaci贸n a trav茅s de logstash o similares.

Se implementa RateLimiter para controlar el numero de peticiones que vamos a permitir y que soporta nuestra aplicaci贸n,
para este ejemplo no se hizo un an谩lisis de cuantas peticiones soporta para saber a partir de que momento se empieza a 
degradar el servicio y poder mirar las opciones de escalar. Podemos pensar a futuro en un escalamiento automatico a 
traves de AppEngine de GCP por ejemplo u otras herramientas existentes.

En un ecosistema de microservicios, es bueno tener un descubridor de servicios que nos ayude a identificar el estado en que 
se encuentra para apoyar a煤n mas el tema del escalamiento, y alg煤n balanceador de carga que nos ayude a redireccionar 
las peticiones a los servicios disponibles.

## Corner Cases

A continuaci贸n se mencionan algunos corner cases:

- Alguien se encuentra el usuario y password y de manera mal intencionada empieza abrir sesiones ilimitadamente.
- Se encuentran una sesi贸n abierta y adicionan muchos operandos hasta saturar los recursos de la maquina y hacer caer el servicio.
- Se cae el servicio en medio de una sesi贸n con muchas operaciones, como se recupera ante alguna falla.
- Se env铆an operaciones con sesiones que no existen.


## Diagrama CI 馃摝

![alt text](https://github.com/enavarrom/operatortest/blob/develop/CI_Diagram.drawio.png?raw=true)



## Diagrama CD 馃摝

![alt text](https://github.com/enavarrom/operatortest/blob/develop/CD_Diagram.drawio.png?raw=true)


## Jenkinsfile 馃摝

[Jenkinsfile](https://github.com/enavarrom/operatortest/blob/main/Jenkinsfile)

Evidencia:

![alt text](https://github.com/enavarrom/operatortest/blob/main/pipeline.jpeg?raw=true)

## Construido con 馃洜锔?


* [Spring](https://spring.io/projects/) - Spring Framework
* [Gradle 7.1.1](https://docs.gradle.org/) - Manejador de dependencias
* [Java 11](https://www.java.com/) 


## Autor 鉁掞笍


* **Eder Navarro** - [enavarrom](https://github.com/enavarrom)

