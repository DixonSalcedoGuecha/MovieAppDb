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

  # Lista de la pantalla Principal

![Screenshot_20221121_043601](https://user-images.githubusercontent.com/80532552/203017138-a43bd2ba-cb86-47f5-b3e7-334395824248.png)

  # Detalle de una Pelicula seleccionada
  
  ![Detalle de la Pelicula](https://user-images.githubusercontent.com/80532552/203019663-ceef309f-6d4b-4157-a4e7-4d9cf670c136.png)


  # Lista de la pantalla Principal pero seleccionando dos favoritos y poniendo una calificacion para 5

![Calificando y agregando a Favoritos](https://user-images.githubusercontent.com/80532552/203017426-cb0a7410-dac5-488f-9c68-6bcaf2f06956.png)

  # Lista de clasificacion segun el usuario

![Ranking de Peliculas](https://user-images.githubusercontent.com/80532552/203017578-d460dc76-d93c-45e9-b631-b68226c873f2.png)

  # Lista de Favoritos segun el usuario

![Lista de Favoritos](https://user-images.githubusercontent.com/80532552/203017702-a254a1da-856a-425f-bc14-d27c70c093b7.png)
