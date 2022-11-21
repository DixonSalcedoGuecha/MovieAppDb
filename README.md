# MovieAppDb

# Características

1. Este proyecto se está realizando con un patrón de arquitectura llamado MVVM
2. Para inyección de dependencias se está usando Hilt
3. Para manejo de datos se está usando Room
4. Para consumir el API gratuita se está usando Retrofit

# Aspectos antes de ejecutar el proyecto

1. La página de donde se está consumiendo esta API gratuita se llama https://www.themoviedb.org/documentation/api
2. Debe registrarse para obtener un toquen de acceso
3. Si desea cambiar el TOKEN DE ACCESO está en el archivo Constans de la carpeta domain
4. Si la página esta caída o no está registrado el app no te mostrara la lista de peliculas
5. Puedes agregar cada pelicula a tus favoritos
6. Puedes listar tus favoritos dando clic en el corazón de la parte superior de la pantalla principal
7. Puedes Calificar las peliculas con un numero de estrellas epecificas para cada pelicula
8. Puedes listar el ranking de las peliculas dando clic en la estrella de la parte superior de la pantalla principal

## Ejecucion de la aplicacion

  # Lista de la pantalla Principal al ingresar por primera vez al app
  
![Screenshot_20221121_043601](https://user-images.githubusercontent.com/80532552/203021000-d90f4aca-9746-4163-b495-53df61a59bc0.png)


  # Detalle de una Pelicula seleccionada
  
  ![Detalle de la Pelicula](https://user-images.githubusercontent.com/80532552/203021123-5995e43b-4ab6-4c6a-91fb-4615100f82f2.png)


  # Lista de la pantalla Principal pero seleccionando dos favoritos y poniendo una calificacion para 5
![Calificando y agregando a Favoritos](https://user-images.githubusercontent.com/80532552/203020428-62094556-522f-4a14-ace8-da935822cae0.png)


  # Lista de clasificacion segun el usuario
  
![Ranking de Peliculas](https://user-images.githubusercontent.com/80532552/203021183-a5420a6b-e526-4097-9743-4bdcf203d2c2.png)


  # Lista de Favoritos segun el usuario

![Lista de Favoritos](https://user-images.githubusercontent.com/80532552/203021235-2956c848-d8d5-44c2-a514-3b413ecfdce1.png)
