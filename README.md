# Operation Test

_Ejemplo de cálculo de operaciones sumas, restas, ...dado uno o varios operandos_


## Comandos Requeridos 📋

```
1. Construir/Empaquetar: gradle clean build -x
2. Dockerizar: gradle bootBuildImage
3. Probar: gradle test
4. Ejecutar contenedor: docker run -d -p 8080:8080 operators:0.0.1-SNAPSHOT
5. CURL probar API: http://localhost:8080/swagger-ui/

```
## Seguridad

Al ser un servicio de prueba se configura seguridad con autenticación básica, 
para consultar el servicio, logicamente para un ambiente productivo ya se puede pensar en Oauth2, 
e integración con autenticación con servicios google, facebook, ldap, keycloak...etc.

para consultar los servicios puede usar el usuario: admin y el password: admin12345


## Arquitectura

![alt text](https://github.com/enavarrom/operatortest/blob/develop/DiagramClass.drawio.png?raw=true)

La solución planteada es la de un microservicio API REST construido con Java y el framework de Spring._
Esta solución permite realizar operaciones matemáticas básicas como suma, restas, multiplicacion, división y potenciación_
a traves de una sesión, a la cual se le envían los operandos, y luego se realiza la operación.

La solución es sencilla dado que el problema en mención es sencillo. Su acceso es mediante peticiones POST y GET a través del controlador 
**_OperationController_** el cual tiene los métodos para crear sesión, agregar operandos a la sesión y ejecutar la operación. Este controlador,
funciona como una fachada para el servicio **_OperationService_** que es el componente que tiene la lógica de negocio apoyado de la clase 
**_OperationSession_** quien es la que se encarga de habilitar el objeto que representa la sesión en la cual se realizarán las operaciones.

Una vez creada la sesión, **_OperationSession_** contiene el método executeOperation el cual usa la invocación de la operación mediante una 
interfaz funcional de tipo **_ExecuteOperation_** el cuál representa la operación a realizar.


## Otros Aspectos de la implementación

Adicional para efectos de la prueba se adicionan escritura de logs usando Sl4j, 
se deja la configuración de logs por defecto en consola, aunque se podría configurar 
y es mejor usar archivos de texto o inclusive centralizar los logs.

Se deja una configuración base y básica para la trazabilidad de las peticiones realizadas mediante Spring sleuth, 
el cual podría integrarse con Zipking. O buscar una implementación a través de logstash o similares.

Se implementa RateLimiter para controlar el numero de peticiones que vamos a permitir y que soporta nuestra aplicación,
para este ejemplo no se hizo un análisis de cuantas peticiones soporta para saber a partir de que momento se empieza a 
degradar el servicio y poder mirar las opciones de escalar. Podemos pensar a futuro en un escalamiento automatico a 
traves de AppEngine de GCP por ejemplo u otras herramientas existentes.

En un ecosistema de microservicios, es bueno tener un descubridor de servicios que nos ayude a identificar el estado en que 
se encuentra para apoyar aún mas el tema del escalamiento, y algún balanceador de carga que nos ayude a redireccionar 
las peticiones a los servicios disponibles.

## Corner Cases

A continuación se mencionan algunos corner cases:

- Alguien se encuentra el usuario y password y de manera mal intencionada empieza abrir sesiones ilimitadamente.
- Se encuentran una sesión abierta y adicionan muchos operandos hasta saturar los recursos de la maquina y hacer caer el servicio.
- Se cae el servicio en medio de una sesión con muchas operaciones, como se recupera ante alguna falla.
- Se envían operaciones con sesiones que no existen.


## Diagrama CI 📦

![alt text](https://github.com/enavarrom/operatortest/blob/develop/CI_Diagram.drawio.png?raw=true)



## Diagrama CD 📦

![alt text](https://github.com/enavarrom/operatortest/blob/develop/CD_Diagram.drawio.png?raw=true)


## Jenkinsfile 📦

[Jenkinsfile](https://github.com/enavarrom/operatortest/blob/main/Jenkinsfile)

Evidencia:

![alt text](https://github.com/enavarrom/operatortest/blob/main/pipeline.jpeg?raw=true)

## Construido con 🛠️


* [Spring](https://spring.io/projects/) - Spring Framework
* [Gradle 7.1.1](https://docs.gradle.org/) - Manejador de dependencias
* [Java 11](https://www.java.com/) 


## Autor ✒️


* **Eder Navarro** - [enavarrom](https://github.com/enavarrom)

