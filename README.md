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

## Arquitectura

![alt text](https://github.com/enavarrom/operatortest/blob/develop/DiagramClass.drawio.png?raw=true)







## Diagrama CI 📦

![alt text](https://github.com/enavarrom/operatortest/blob/develop/CI_Diagram.drawio.png?raw=true)



## Diagrama CD 📦

![alt text](https://github.com/enavarrom/operatortest/blob/develop/CD_Diagram.drawio.png?raw=true)


## Construido con 🛠️


* [Spring](https://spring.io/projects/) - Spring Framework
* [Gradle 7.1.1](https://docs.gradle.org/) - Manejador de dependencias
* [Java 11](https://www.java.com/) 


## Autor ✒️


* **Eder Navarro** - [enavarrom](https://github.com/enavarrom)

