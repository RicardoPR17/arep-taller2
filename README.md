# **Taller 2 - Diseño y estructuras de aplicaciones distribuidas en internet**
### *Hecho por Ricardo Pulido Renteria*

Siguiendo con el proceso del taller anterior, el servidor web creado con Java y añadiendo archivos almacenados en el disco. Ahora esta habilitado para que el usuario pueda solicitar estos recursos y el servidor realizará todo el proceso requerido para enviar el recurso solicitado.

## **Descarga y ejecución**

Para poder ejecutar este proyecto, el cual se ejecutará en tu ambiente local por fines de desarrollo y pruebas, debes contar con algunos elementos que serán indicados a continuación.


## **Prerequisitos**

La ejecución de este proyecto requiere de:
- `Java (versión 8 o superior)`
- `Maven (3.8.1 o superior)`
- `Conexión a internet`


## **Instalación**

Para poder trabajar con el proyecto, es necesario descargarlo desde GitHub. Para esto puede clonar el repositorio en su máquina o puede descargarlo en formato zip. Luego, una vez acceda al directorio del proyecto, debe ejecutar el comando `mvn install` para descargar las dependencias del proyecto, ya sea desde la terminal de comandos o desde la terminal que le brinde el intérprete de código de su preferencia (VS Code, IntelliJ, NetBeans, etc).

Para ejecutarlo, podrá hacerlo desde la terminal de comandos como se explica a continuación. O desde el intérprete de código de su elección, haciendo `run` o ejecutando el código de la clase _HttpServer_.


- **_Ejecución usando terminal de comandos_**
  
  En caso de realizar la ejecución desde la terminal de comandos, se debe realizar lo siguiente:
  1. Acceder al directorio del proyecto usando el comando `cd arep-taller2`.
  2. Una vez dentro del directorio del proyecto, se ejecuta el comando `mvn package` para generar la carpeta _target_.
  3. Desde la terminal, ejecutamos el comando `java -cp .\target\classes com.example.Taller2.HttpServer`.
  4. Listo, el servidor web estará corriendo y verás un mensaje diciendo que está listo para recibir peticiones.


## **Uso**

Primero, vamos a acceder  a través de un navegador web a nuestro servidor HTTP creado. Esto, por medio de la ruta http://localhost:17000/ que por si sola nos mostrará una página de error. Pero, si añadimos elementos al path obtendremos diferentes recursos, estos son:

+ Página web con formularios: http://localhost:17000/public.html
+ Código JavaScript de los formularios: http://localhost:17000/public.js
+ Página web API películas: http://localhost:17000/movie.html
+ Código JavaScript API películas: http://localhost:17000/movieRequest.js
+ Hoja de estilos página web API películas: http://localhost:17000/movie.css
+ Imagen camara de cine:  http://localhost:17000/camera.png
+ Imagen cubo de rubik: http://localhost:17000/cube.jpg
+ GIF cubo de rubik: http://localhost:17000/scramble.gif

El servicio de la API fue tomado del taller anterior, refactorizado para separar sus componentes y mantener su operación. En ese caso, al ser un HTML con diferentes elementos, el servidor irá entregando los recursos a medida que los solicita el navegador, ya sea el código de JS, los estilos del CSS o la imágen.

## **Diseño**

Para este proyecto se manejan una clase que es _HttpServer_, tomando de guía las clases encontradas en el directorio _labClases_ las cuales se realizaron durante la sesión de laboratorio.

En el servidor se cuenta con los elementos necesarios para levantar el servicio y escuchar por el puerto 17000, el cual se puede cambiar solo con modificar este valor que se encuentra en el enumerado llamado _env_.

A nivel de arquitectura, se manejaron 2 directorios. El primero y principal del proyecto que es _Taller2_ donde encontramos el código que inicia el servidor y maneja las peticiones que se realicen desde un cliente (el navegador). El segundo directorio es _resources/public_, aquí encontramos todos los recursos que el servidor ofrece y envía cuando son solicitados.

Gracias a esta distribución de archivos en carpetas, es posible tener una separación clara entre la lógica del servidor y el espacio de almacenamiento de los recursos. Además de independizarlos de forma tal que se pueden añadir o eliminar recursos sin afectar la operación del servidor; por su parte, añadir nuevas funciones al servidor o modificarlo sin alterar la integridad de los recursos ofrecidos.

## **Pruebas**

Para estas pruebas, vamos a acceder a cada uno de los recursos. Esto con el fin de evidenciar el llamado independiente de cada uno de ellos y la correcta carga de los recursos. Para eso, se usará el navegador de Firefox y el apartado de red de su inspección de recursos.

+ Página web con formularios: http://localhost:17000/public.html

![Formularios](<Imágenes README/image.png>)

Dado que esta página tiene un script para su operación en otro archivo, vemos que el navegador solicita el archivo JS después de recibir el HTML.

+ Código JavaScript de los formularios: http://localhost:17000/public.js

![JS formularios](<Imágenes README/image-1.png>)

En ese caso, al traer solo el código, se muestra en formato de texto pero no solicita ningún otro archivo para su carga.

+ Página web API películas: http://localhost:17000/movie.html

![API](<Imágenes README/image-5.png>)

Aquí, vemos que trae más recursos para cargar la página. Desde el HTML inicial hasta una imágen que requiere la página para ser presentada adecuadamente para el usuario y poder operar.

+ Código JavaScript API películas: http://localhost:17000/movieRequest.js

![API JS](<Imágenes README/image-3.png>)

Tal como en JavaScript anterior, se presenta el código que permite el funcionamiento de la página de la API.

+ Hoja de estilos página web API películas: http://localhost:17000/movie.css

![API CSS](<Imágenes README/image-4.png>)

Aquí, se presenta la hoja de estilos para la página de la API con el manejo de sus respectivas etiquetas.

+ Imagen camara de cine:  http://localhost:17000/camera.png

![Camara](<Imágenes README/image-6.png>)

Aquí, se carga una imagen con extensión PNG la cual es utilizada en la página de API.

+ Imagen cubo de rubik: http://localhost:17000/cube.jpg

![Cubo](<Imágenes README/image-8.png>)

Para probar la muestra de otros formatos de imagen, se prueba también con la carga de una imagen con extension JPG.

+ GIF cubo de rubik: http://localhost:17000/scramble.gif

![Boom](<Imágenes README/image-9.png>)

Probando con extensiones de imagen dinámicas, se prueba con un GIF el cual se carga y realiza su animación de forma adecuada.


## **Construido con**
  - [Git](https://git-scm.com) - Control de versiones
  - [Maven](https://maven.apache.org) - Administrador de dependencias
