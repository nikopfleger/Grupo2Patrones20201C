# MyHibernate - Grupo 2

Este repositorio contiene el codigo relacionado a la creación del T.P. de la materia Patrones Algoritmicos para Estructuras Avanzadas dictada en la U.T.N.

## Requisitos para que el proyecto funcione localmente

Se debe de crear un archivo .classpath con el siguiente contenido o en caso de ya tener el archivo creado copiar el texto a continuacion dentro del mismo.
Este texto hace referencia a las dependencias del proyecto.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<classpath>
	<classpathentry kind="src" path="src"/>
	<classpathentry kind="src" path="resources"/>
	<classpathentry kind="con" path="org.eclipse.jdt.launching.JRE_CONTAINER"/>
	<classpathentry kind="lib" path="C:/Users/Franco/git/myhibernate-patrones-grupo2/src/hsqldb_6114.jar"/>
	<classpathentry kind="lib" path="C:/Users/Franco/git/myhibernate-patrones-grupo2/src/byte-buddy-1.10.10.jar"/>
	<classpathentry kind="lib" path="C:/Users/Franco/git/myhibernate-patrones-grupo2/src/javassist-3.26.0-GA.jar"/>
	<classpathentry kind="lib" path="C:/Users/Franco/git/myhibernate-patrones-grupo2/src/reflections-0.9.12.jar"/>
	<classpathentry kind="output" path="bin"/>
</classpath>
```
Una vez copiado el contenido se debe de cambiar la ubicación de estas dependecias por las que estan en el proyecto.

## Dependecias utilizadas

Las dependecias que se utilizaron son las siguientes:
* HSQLDB
* ByteBuddy
* Reflections
* Javaassist (necesaria para poder correr Reflections)
