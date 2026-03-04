# Práctica 2
Reconocimiento de palabras con Autómatas Finitos Deterministas
## Instituto de Ciencias Basicas e Ingenieria
### Realizado por García Colmenares Andrés

---

# Introducción

Los Autómata Finito Deterministas (AFD) son modelos, los cuales son utilizados en la teoría de la computación para el reconocimiento de lenguajes formales. Este tipo de autómata permiten la identificacion de cadenas de símbolos pertenecientes a un alfabeto determinado y a partir de esto definir si es que estos pertenecen a un lenguaje especifico.

##### Definición por el manual de practicas: 
Un Autómata Finito Determinista (AFD) se define como una tupla:

AFD = (Σ, Q, δ, q0, F)

donde:

Σ es el alfabeto de entrada.  
Q es el conjunto finito y no vacío de estados.  
δ es la función de transición δ: Q × Σ → Q.  
q0 ∈ Q es el estado inicial.  
F ⊂ Q es el conjunto de estados finales o de aceptación (𝐅≠∅).

Sea A = (Σ, Q, δ, q0, F) un AFD y sea w = 𝑤1, 𝑤2,…,𝑤n donde wi ∈ Σ.
Entonces, A acepta w si existe una secuencia de estados 𝑟,𝑟,…,𝑟 ∈ 𝑄 con tres condiciones:
1. 𝑟0=𝑞0
2. 𝛿(𝑟i,𝑤i+1)=𝑟+1 𝑝𝑎𝑟𝑎 𝑖=0,1,…,𝑛−1 
3. 𝑟n ∈ 𝐹
La condición 1 establece que el AFD comienza en el estado inicial. 
La condición 2 establece que el AFD cambia desde un estado hacia otro estado de acuerdo con la función de transición. 
La condición 3 establece que el AFD acepta la cadena de entrada si termina en un estado de aceptación. Entonces, A reconoce el lenguaje L si 𝐿={𝑤|𝐴 𝑎𝑐𝑒𝑝𝑡𝑎 𝑤}.

Estos automatas, han sido de una gran ayuda para su uso en compiladores o analisis lexicos, etc.

---

# Marco teórico

Los automatas permite el estudio de modelos abstractos, con la creacion de maquinas capaces de reconocer lenguajes formales. Y dentro de esto, se encuentan los **Automatas Finitos Deterministas**, estos tienen en sus caracteristicas un conjunto finito de estados, o tambien un unico estado de transicion posible

Para la creacion de un automata, se usa una tabla de transición, la cual muestra que estado usara el automata dependiendo del estado y de su simbolo, con la misma tabla, permite la representacion de un automata de una forma simple y estructurada. 
Complementario a esto, se usan los diagramas de transicion de estados para la representacion grafica de un automata, con esto se puede ver su funcionamiento de una forma visual.

En el diagrama de transicion, cada estado de un automata tiene diferentes formas de representacion y un orden, como el **estado inicial**, que es representado por una flecha sin nodo de origen, y el **estado final**, que se representa como un doble circulo.

Cada parte de un automata permite la identificacion y aumentar la claridad sobre como funciona un AFD, para facilitar un analisis de las cadenas.

---

# Objetivos

## Objetivo general

Crear un Automata Finito Determinista, para poder reconocer palabras de un lenguaje, usando cada parte de un Automata Finito Determinista, como las tablas de transicion, diagramas de transicion de estados o simulaciones
## Objetivos específicos

* Creacion de Autómatas Finitos Deterministas (AFD).
* Creacion de tablas de transición de estados.
* Creacion de diagramas de transición de estados.
* Creacion y simulación para AFD para la validacion de palabras válidas y/o no válidas en el lenguaje.

---

# Desarrollo

Para el desarrollo de la actividad, se desarrollaran los ejercicios de la practica, donde cada ejercicio tiene los siguientes elementos

1. Tupla del AFD (𝚺, 𝐐, 𝛅, 𝐪𝟎, 𝐅)
2. Tabla de transición
3. Diagrama de transición
4. Simulación con palabras aceptadas y rechazadas

Las simulaciones fueron realizadas utilizando el simulador de autómatas disponible en:

https://automatonsimulator.com/

# Ejercicio 1

L = {0x | x ∈ {0,1}\*}

## Tupla

AFD = (Σ,Q,δ,q0,F)  
Σ = {0,1}  
Q = {q0,q1,q2}  
q0 = inicial  
F = {q1}

## Tabla de transición

| Estado | 0   | 1   |
| ------ | --- | --- |
| -> q0  | q1  | q2  |
| F q1   | q1  | q1  |
| q2     | q2  | q2  |

## Palabras aceptadas (5 transiciones)

00000  
01011  
01111
01101  
01110

## Palabras rechazadas (5 transiciones)

11100  
11011  
11101  
11010  
11011

---

# Ejercicio 2

L = {x1 | x ∈ {0,1}\*}

## Tupla

AFD = (Σ,Q,δ,q0,F)  
Σ = {0,1}  
Q = {q0,q1}  
q0 = inicial  
F = {q1}

## Tabla de transición

| Estado | 0   | 1   |
| ------ | --- | --- |
| -> q0  | q0  | q1  |
| F q1   | q0  | q1  |

## Palabras aceptadas (5 transiciones)

00111
11111
00001
01101
00101
## Palabras rechazadas (5 transiciones)

00000
01010
10000
10100
11100


---

# Ejercicio 3

L = {x01y}

## Tupla

AFD = (Σ,Q,δ,q0,F)  
Σ = {0,1}  
Q = {q0,q1,q2}  
q0 = inicial  
F = {q2}

## Tabla de transición

| Estado | 0   | 1   |
| ------ | --- | --- |
| -> q0  | q1  | q0  |
| q1     | q1  | q2  |
| F q2   | q2  | q2  |

## Palabras aceptadas (5 transiciones)

00101  
00110  
00111  
01001  
01010

## Palabras rechazadas (5 transiciones)

00000  
10000  
11000  
11100  
11110

---

# Ejercicio 4

L = {x110y}

## Tupla

AFD = (Σ,Q,δ,q0,F)  
Σ = {0,1}  
Q = {q0,q1,q2,q3}  
q0 = inicial  
F = {q3}

## Tabla de transición

| Estado | 0   | 1   |
| ------ | --- | --- |
| -> q0  | q0  | q1  |
| q1     | q0  | q2  |
| q2     | q3  | q2  |
| F q3   | q3  | q3  |

## Palabras aceptadas (5 transiciones)

00010
11000
11010
11011
11001

## Palabras rechazadas (5 transiciones)

00000
01010
11111
10000
10001

---

# Ejercicio 5

L = {acxab}

## Tupla

AFD = (Σ,Q,δ,q0,F)  
Σ = {a,b,c}  
Q = {q0,q1,q2,q3,q4}  
q0 = inicial  
F = {q4}

## Tabla de transición

| Estado | a   | b   | c   |
| ------ | --- | --- | --- |
| -> q0  | q1  | -   | -   |
| q1     | -   | -   | q2  |
| q2     | q3  | q2  | q2  |
| q3     | -   | q4  | -   |
| F q4   | q4  | q4  | q4  |

## Palabras aceptadas (5 transiciones)

acaba
acabb
acabc
acbab
accab

## Palabras rechazadas (5 transiciones)

abbbb  
bbbbb 
cccccc 
abccc  
abbbc

---

# Ejercicio 6

L = {acxz | z ≠ ab}

## Tupla

AFD = (Σ,Q,δ,q0,F)  
Σ = {a,b,c}  
Q = {q0,q1,q2,q3}  
q0 = inicial  
F = {q2}

## Tabla de transición

| Estado | a   | b   | c   |
| ------ | --- | --- | --- |
| -> q0  | q1  | -   | -   |
| q1     | -   | -   | q2  |
| F q2   | q2  | q2  | q2  |
| q3     | q3  | q3  | q3  |

## Palabras aceptadas (5 transiciones)

acaaa
acbbb
acbab
acbbc
acbba
## Palabras rechazadas (5 transiciones)

bbbbb
ccccc
bbaab
cabac
bcabc

---

# Ejercicio 7

L = {acbxz | z ≠ bd}

## Tupla

AFD = (Σ,Q,δ,q0,F)  
Σ = {a,b,c,d}  
Q = {q0,q1,q2,q3}  
q0 = inicial  
F = {q3}

## Tabla de transición

| Estado | a   | b   | c   | d   |
| ------ | --- | --- | --- | --- |
| -> q0  | q1  | -   | -   | -   |
| q1     | -   | -   | q2  | -   |
| q2     | -   | q3  | -   | -   |
| F q3   | q3  | q3  | q3  | q3  |

## Palabras aceptadas (5 transiciones)

acbab
acbdd
acbac
acbad
acbaa

## Palabras rechazadas (5 transiciones)

abcda
bbcda
ccdab
caacb
ddcba

---

# Ejercicio 8

L = {cabaxz | z ≠ ab}

## Tupla

AFD = (Σ,Q,δ,q0,F)  
Σ = {a,b,c,d}  
Q = {q0,q1,q2,q3,q4}  
q0 = inicial  
F = {q4}

## Tabla de transición

| Estado | a   | b   | c   | d   |
| ------ | --- | --- | --- | --- |
| -> q0  | -   | -   | q1  | -   |
| q1     | q2  | -   | -   | -   |
| q2     | -   | q3  | -   | -   |
| q3     | q4  | -   | -   | -   |
| F q4   | q4  | q4  | q4  | q4  |
## Palabras aceptadas (5 transiciones)

cabaa
cabab
cabac
cabad
cabaab

## Palabras rechazadas (5 transiciones)

abcda
abbac
abbbc
dddab
bbacd

---

# Ejercicio 9

L = {aⁿcbᵐ | n>0 , m>0}

## Tupla

AFD = (Σ,Q,δ,q0,F)  
Σ = {a,b,c}  
Q = {q0,q1,q2,q3}  
q0 = inicial  
F = {q3}

## Tabla de transición

| Estado | a   | b   | c   |
| ------ | --- | --- | --- |
| -> q0  | q1  | -   | -   |
| q1     | q1  | -   | q2  |
| q2     | -   | q3  | -   |
| F q3   | -   | q3  | -   |

## Palabras aceptadas (5 transiciones)

aacbb
acbbb

## Palabras rechazadas (5 transiciones)

bbbbb
ccccc
bcbcb
abbbb
aaaaa

---

# Ejercicio 10

L = {x cᵐ | x ∈ {a,b}\* y número de b es par}

## Tupla

AFD = (Σ,Q,δ,q0,F)  
Σ = {a,b,c}  
Q = {q0,q1,q2}  
q0 = inicial  
F = {q0,q2}

## Tabla de transición

| Estado   | a   | b   | c   |
| -------- | --- | --- | --- |
| ->: F q0 | q0  | q1  | q2  |
| q1       | q1  | q0  | q2  |
| F q2     | q2  | q2  | q2  |

## Palabras aceptadas (5 transiciones)

aaaaa
bbaaa
aaaac
acabc
acbba

## Palabras rechazadas (5 transiciones)

baaaa
babba



---

# Resultados

Para la practica, se crearon varios Automatas Finitos Deterministas, con cada ejercicio, se definieron los estados del automata, como sus funciones, sus transiciones, y sus estados de aceptacion.

Cada tabla de transicion permitio que el comportamiento de cada automata se representara de una manera organizada, mientras que cada diagrama de estado facilita la visualizacion de sus estaodos y la transicion 

Ademas de esto, con el uso del simulador de automatas se pudieron realizar pruebas con diferentes cadenas de entrada para la verificacion si es que son aceptadas, o rechazadas con facilidad, asi confirmando que los automatas reconocen la cadena dada

---

# Cuestionario

## ¿Cuáles son los elementos que definen un AFD?

Un AFD tiene lo siguiente
* Un alfabeto de entrada 
* Un conjunto de estados
* Una función de transición 
* Un estado inicial
* Un conjunto de estados finales.

---

## ¿Cuál es la utilidad de una tabla de transiciones de estado de un AFD?

La representacion de estados para la verificacion de datos y como estos transicionan de un lado a otro con una entrada

---

## ¿Qué importancia tienen los diagramas de transición de estado en el proceso de construcción de un AFD?

La visualizacion de estados en forma grafica, siendo clara y visual, ademas de la muestra de sus simbolos del alfabeto 

---

## ¿Cuáles son las ventajas de la simulación del AFD?

Simular permite la comprension y conocimiento de los estados de transicion, ademas de apoyar en el aprendizaje de un AFD 

---

# Conclusiones

Los automatas finitos Deterministas, son una herramienta necesaria y fundamental para la computacion, especialmente para el reconocimiento de lenguajes formales, con esto, se puede realizar la identificacion de estados y transiciones para modelar un sistema el cual procesa cadenas de simbolos.

Con esta practica aprendi a comprender como funciona un AFD, ademas de la contruccion de cada elemento, como tablas de transicion o representaciones graficas

Estos modelos han sido importantes, ya que tiene aplicaciones importantes en el diseño de compiladores, como tambien analisis lexicos, o diccionarios 

---

# Bibliografía

Giró, J., Vázquez, J., Meloni, B., Constable, L. (2015).  
Lenguajes formales y teoría de autómatas. Editorial Alfaomega.

Ruiz Catalán, J. (2010).  
Compiladores: teoría e implementación. Editorial Alfaomega.

Brookshear, J. G. (1995).  
Teoría de la Computación: Lenguajes formales, autómatas y complejidad. Addison-Wesley Iberoamericana.

