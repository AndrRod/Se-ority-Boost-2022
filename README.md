# CHALLENGE BACKEND - Java - Spring Boot (API REST) 🚀

## Consigna
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