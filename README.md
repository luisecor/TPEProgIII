# TPE Programación 3

En este trabajo se desarrollará la lectura de archivos .csv, en los cuales habrá libros con sus respectivos géneros y páginas totales que tiene cada uno.
Fecha

01/07/2022

Integrantes

-   Codan, Pedro Miguel ([codanpedro@gmail.com](mailto:codanpedro@gmail.com))
    

- Cordoba, Luis Ezequiel ([luchitocordoba.LC@gmail.com](mailto:luchitocordoba.LC@gmail.com) )



# Introducción

  

En el siguiente informe se resuelve la segunda entrega del Trabajo Práctico Especial de la materia Programación 3.

Los objetivos del trabajo son, por un lado, realizar la lectura de archivos .csv los cuales contienen una lista de géneros representando el orden en que fueron buscados. Por otro lado, se realiza la construcción de distintas funciones para dar respuesta a los problemas planteados, para lo cual se implementaron los distintos conceptos vistos en la materia para optimizar el funcionamiento y el costo computacional. En este sentido, se notó una gran analogía con las estructuras de grafos vistas en la materia, por lo que decidimos realizarlos con dicha estructura, considerando los distintos costos computacionales intervinientes.
# Desarrollo

### Análisis e implementación

En una primera lectura de la consigna, identificamos la designación de las clases Grafo, GrafoDirigido, Arco, LeeYEscribe. Esta última clase se encarga de la lectura de los archivos, solo instancia los libros al leer el archivo .csv. Por otro lado, la clase Arco contiene un vértice destino de tipo String, haciendo referencia hacia quien se dirige y un valor de tipo int que nos indica la cantidad de veces que se realizó ese arco entre los vértices, acompañado de las respectivas funciones para solicitar dicha información. A su vez, cuenta con un vértice origen ya que en algunos casos no estamos parados sobre un vértice en particular sino que desde afuera necesitamos agregar arcos entre dos vértices determinados.

Por otro lado, La clase GrafoDirigido posee un solo atributo “estructura” del tipo HashMap teniendo como clave los géneros (tipo String) y asociado a cada uno de ellos un arreglo de arcos tipo ArrayList<Arco>. A su vez, posee las funciones para poder agregar vértices y arcos al grafo que contiene la clase, además de poseer las funciones principales que dan respuesta a los problemas planteados, como es “generosMasBuscados”, “secuenciaMasLarga” y “BacktrackingObtenerSubGrafo”.

En el caso del ArrayList<Arco> utilizado dentro del HashMap, nos pareció lo más pertinente ya que nos permitía ir almacenando los distintos arcos asociados a un vértice de una forma ordenada (según el peso del arco). Esto nos permitía poder acceder a los arcos más pesados en O(1) ya que sabíamos que se encontraba en la primera posición, sin embargo hay que sumarle el costo de ordenarlos una vez que ingresamos todos los géneros adyacentes (O(log2n . n )). Pero lo que no hay que dejar de tener en cuenta es que, se ordena una única vez al leer el archivo .csv, por ende si se pide reiteradas veces una funcionalidad ya se tiene una gran ventaja.

En el caso del Grafo sólo se implementó como una interfaz por si en un futuro era necesario realizar algo con grafos no dirigidos.

Adentrándonos en la resolución de los distintos servicios, el primer inciso solicita devolver los N géneros más buscados luego de un vértice dado. Considerando que los arcos asociados a un vértice están ordenados de forma descendente, para dar respuesta al problema fue necesario traer los arcos y devolver los vértices destino de los primeros N arcos. Logrando así una complejidad O(N), siendo N la cantidad de arcos solicitada por el usuario, gracias a tener los arcos ordenados en un principio.

Aquí notamos la particularidad de tener los adyacentes guardados como arcos, y no como vértices. Lo cual fue desarrollado de esta manera para poder llevar el peso que tienen los adyacentes.

Ya para el 2do inciso, el cual solicita que “a partir de un género A encontrar, en tiempo polinomial, la secuencia de géneros que más alto valor de búsqueda posee. Vamos a definir el valor de búsqueda de una secuencia como la suma de los arcos que la componen.”. Interpretando aquí como la suma del peso de los arcos y no la cantidad de arcos, y buscando un tiempo polinomial de ejecución, se nos presenta una semejanza importante con Greedy. Siendo nuestros candidatos los géneros adyacentes, y el método de selección es agarrar el género adyacente más pesado que se encuentre disponible.

Esta estrategia no nos proporciona a ciencia cierta obtener la secuencia con las mayor suma de los arcos, pero si en caso de buscarla en un tiempo polinomial, el cual en este caso es O(n2) ya que por cada vértice al cual avanza agarra todos sus adyacentes (no basta con traer el primero ya que puede que esté visitado, por ende sería necesario avanzar).

En la gráfica proporcionada en la Figura 1, se logra percibir una gran diferencia entre la complejidad de procedimientos con O(n) siendo este el valor representativo para el primer servicio. El mismo, al compararlo con los otros dos servicios se logra notar una grán diferencia, ya sea de 8 entradas en memoria contra un algoritmo de ordenamiento utilizado al cargar los géneros, y 48 entradas en memoria (40 + 8 planteadas en la figura 1) en caso de compararlo contra un algoritmo de Greedy. Siempre considerando el caso que n sea 8, pero si n es notoriamente más grande las diferencias pasan a ser dramáticamente más grandes, especialmente entre el ordenamiento y Greedy, donde la diferencia se proporciona por la diferencia entre n . n o bien log2n . n.

### Ejecución en Main

Dentro del main contamos con los comandos “LeeYEscribe.cargarCSVGeneros(g1);” para poder cargar el CSV (dentro de la clase LeeYEscribe se deja registro del csv que se desea leer). Una función “g1.imprimir();” para imprimir el grafo y lograr ponerlo un graficador de grafos. Seguido de ello se da pie al primer servicio solicitado en el práctico, ejecutando la función “System.out.println(g1.generosMasBuscados("historia", 4));” donde buscamos por el género Investigación y sus 4 géneros más buscados a continuación. El mismo nos da una respuesta del siguiente estilo: [investigación 1, terror 1]. Corroborando con el gráfico, vemos:
