WINDOWS
- Ejecutar archivo .jar y seguir los pasos.

MAC
1. Ejecutar en consola:
java -jar CodificadorRSA.jar

* En caso de no poder ejecutar, abrir proyecto en eclipse y ejecutar con la maquina virtual de java.


Codificador CodificadorRSA

1. La aplicaci ́on recibe M, n y e. M es un string no vac ́ıo (que tiene u ́ni- camente letras de la A a la Z, en mayu ́sculas, sin incluir la N ̃). n es un producto de dos primos p y q (de no m ́as de 10 d ́ıgitos cada uno) y e un entero positivo primo relativo con (p − 1)(q − 1). Usted no debe gene- rar estos 3 nu ́meros, solo recibirlos como par ́ametro. Puede suponer que cumplen con todas las propiedades establecidas.

2. La aplicaci ́on convierte las letras del mensaje M en bloques, aplica RSA a cada bloque y pega los bloques para retornar el mensaje cifrado C, como se describi ́o m ́as arriba.

3. El c ́odigo de la aplicaci ́on debe incluir un m ́etodo exp que recibe a,b,n enteros como par ́ametro y devuelve ab m ́od n. Usted puede implementar este m ́etodo como desee.

4. No se requiere que su aplicaci ́on tenga interfaz gr ́afica, podr ́ıa correr u ́ni- camente en una l ́ınea de comandos.
Por ejemplo, si la aplicaci ́on recibe M = “HOLA′′, n = 2537, e = 13, debe retornar C = 11911906.
La aplicaci ́on ser ́a sustentada en horarios convenidos con el monitor, quien asignar ́a una m ́axima nota de 0,6, la cual ser ́a sumada a la nota del segundo parcial sobre 5.
