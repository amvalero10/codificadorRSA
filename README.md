WINDOWS
- Ejecutar archivo .jar y seguir los pasos.

MAC
1. Ejecutar en consola:
java -jar CodificadorRSA.jar

* En caso de no poder ejecutar, abrir proyecto en eclipse y ejecutar con la maquina virtual de java.


**Codificador RSA**

1. La aplicación recibe M, n y e. M es un string no vacío (que tiene unicamente letras de la A a la Z, en mayusculas, sin incluir la Ñ). n es un producto de dos primos p y q (de no más de 10 dígitos cada uno) y e un entero positivo primo relativo con (p − 1)(q − 1). no debe generar estos 3 números, solo recibirlos como parámetro.

2. La aplicación convierte las letras del mensaje M en bloques, aplica RSA a cada bloque y pega los bloques para retornar el mensaje cifrado C.

3. El código de la aplicación  incluye un método exp que recibe a,b,n enteros como parámetro y devuelve ab mod n.
