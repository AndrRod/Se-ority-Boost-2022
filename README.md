# CHALLENGE BACKEND - Java - Spring Boot (API REST) 🚀

## CONSIGNA
- El objetivo de este challenge es desarrollar una API REST siguiendo las especificaciones que
indicaremos luego. Este servicio analizará textos y devolverá conteos acerca de caracteres y
palabras. Todas las interacciones tendrán efecto sobre la base de datos para alguna operación
de inserción, lectura o eliminación.
- La API REST consiste de 4 endpoints. Todas las rutas de los endpoints deben llamarse “/text”.
Solo se aceptarán peticiones con los métodos GET, POST, DELETE.
Esta API es como una API pública. Es decir, no requiere de ningún mecanismo de autenticación
de usuarios. Tampoco es necesario validar más de lo que te pidamos, ni implementar seguridad.
Lo único que pedimos es que configures CORS para que cualquier frontend pueda llamarla
desde cualquier URL, sin ser bloqueado por el navegador. (Más información sobre CORS)
La base de datos deberás hacerla según lo que consideres necesario para cumplir la consigna.
¡Cada detalle es importante! Todas las respuestas de todos los endpoints deben devolver JSON
siempre, incluso si sucede un error.

## REQUERIMIENTOS 📖

- 👉 La API REST consiste de 4 endpoints. Todas las rutas de los endpoints deben llamarse “/text”.
  Solo se aceptarán peticiones con los métodos GET, POST, DELETE.
- 👉 Esta API es como una API pública. Es decir, no requiere de ningún mecanismo de autenticación
- 👉 Manejo de Excepciones.
- 👉 Cors: configurado para que pueda acceder desde cualquier URL.
- 👉 Utilización de Spring Boot.
- 👉 Para probar la Api Rest se dejo guardado una coleccion de postman dentro del proyecto.

## hard-coded
- 👉 En clase principal (SeniorityBoost2022Aplication) fueron harcodeados datos para probar la base de datos sin necesadidad de que el usuario ingrese los datos, por lo que para anular los datos ingresados directamente comentar las lineas de codigo correspondiente.

## 1. Modelado de Base de Datos
### De la siguiente entidad registra:
**TEXT :**
- Id (Long - Autogenerado)
- text (String) - se almacenará pero no se mostrara.
- hash (String) - hasheado con Argon2.
- chars (Int).
- result (HashMap<String, Number>).


## 2. TEXT (CRUD)


- Para crear un nuevo text, se hace una validación que consiste en no guardar otro Text si se encuentra registrado text con la misma cantidad de chars.
- Chars: Tiene un valor por defecto (2) si el usuario ingresa uno menor se guarda el valor por defecto.
- Los unicos parametros accesibles para el usuario son text y chars, los demas son ignorados (@JsonIgnore)

### POST
	http://localhost:8080/text/


Ejemplo entrada:

    {
    "text": "holahola",
    "chars": 2
    }

Ejemplo salida:

    {
    "id": 1,
    "url": "/text/1"
    }

Ejemplo de salida cuando se intenta guardar el mismo text y chars:

    {
    "message": "The same text 'holahola' is already registered with the same chars 2",
    "error": true,
    "code": 400,
    "url": "/text/"
    }


### DELETE by ID

	http://localhost:8080/text/{id}

Ejemplo entrada:

    http://localhost:8080/text/1

Ejemplo salida si el text no es encontrado:

    {
    "message": "Text not found",
    "error": true,
    "code": 404,
    "url": "/text/1"
    }

### GET by ID
    
    http://localhost:8080/text/{Id}

Ejemplo de entrada:

    http://localhost:8080/text/1

Ejemplo salida correcta: 

    {
    "id": 1,
    "hash": "$argon2id$v=19$m=1024,t=1,p=1$37UmPFXJOmYtM4/P1k9+bw$w1N5wldXVv5VUPHdK5XJ6WAvxkUfzd9F+poJ7JjC164",
    "chars": 3,
    "result": {
        "a a": 1,
        "hol": 1,
        "ndr": 1,
        "es": 1
    }

Ejemplo salida text no encontrado:

    {
    "message": "Text not found",
    "error": true,
    "code": 404,
    "url": "/text/23"
    }

Ejemplo salida erronea:

    {
    "message": "Error: For input string: \"232d\"",
    "error": true,
    "code": 422,
    "url": "/text/232d"
    }

### Búsqueda de entidad Text

Parámetros del query string:
El usuario puede ingresar todos los parametros o solo uno de ellos debido a que los tres son opcionales.
- Chars (opcional): En el caso de que el usuario no elija ningun numero se realizara la busqueda por Page y rpp.
- Page (opcional): valor por defecto es 0 en el caso de que el usuario no ingreso un valor o este sea inferior a 0;
- rpp (opcional): valor por defecto entre 10 y 100, dependiendo si el usuario ingresa un numero menor o mayor a los citados.


Busqueda por chars, page y rpp

    http://localhost:8080/text?page={page}&rpp={rpp}&chars={chars}

Ejemplo entrada:

    http://localhost:8080/text?page=0&rpp=11&chars=5

Ejemplo de entrada de dato erroneo:

    http://localhost:8080/text?page=d1

Ejemplo de salida de dato erroneo:

    {
    "message": "Error: For input string: \"d1\"",
    "error": true,
    "code": 422,
    "url": "/text"
    }



______________

Hecho por  [Andres Rodriguez](https://github.com/AndrRod/ "Andres Rodriguez") 🎁