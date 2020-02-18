# ARSW-LAB4

###  PART I

- Integrate to the base project supplied the Beans developed in the previous exercise. Just copy the classes, NOT the configuration files. Rectify that the dependency injection scheme is correctly configured with the @Service and @Autowired annotations.

    ![](/BLUEPRINTS-PART2/img/1.jpg)

- Modify the persistence bean InMemoryBlueprintPersistence so that by default it is initialized with at least three other planes, and with two associated with the same author.

    ![](/BLUEPRINTS-PART2/img/2.jpg)

- Configure your application to offer the resource /blueprints, so that when a GET request is made, return in JSON format - all the drawings. For this:
    - Modify the BlueprintAPIController class taking into account the following example of a REST controller made with SpringMVC/SpringBoot

    - Have the BlueprintServices type bean injected into this class (which, in turn, will be injected with its persistence and point filtering dependencies).
    
    ![](/BLUEPRINTS-PART2/img/3.jpg)
    
- Verify the operation of the application by launching the application with maven. And then sending a GET request to: http://localhost:8080/blueprints. Rectify that, in response, a JSON object is obtained with a list containing the detail of the drawings provided by default, and that the corresponding point filtering has been applied.

    ![](/BLUEPRINTS-PART2/img/compilar.jpg)
    
    ![](/BLUEPRINTS-PART2/img/ejecutar.jpg)
    
    ![](/BLUEPRINTS-PART2/img/pag.jpg)
    
- Modify the controller so that it now accepts GET requests to the resource /blueprints/{author}, which returns using a JSON representation all the plans made by the author whose name is {author}. If there is no such author, you must respond with the HTTP error code 404. For this, review in the Spring documentation, section 22.3.2, the use of @PathVariable. Again, verify that when making a GET request -for example- to the resource http://localhost:8080/blueprints/juan, the set of planes associated with the author 'juan' is obtained in JSON format (adjust this to the names of author used in point 2).

    ![](/BLUEPRINTS-PART2/img/autor.jpg)

    ![](/BLUEPRINTS-PART2/img/autor1.jpg)

- Modify the controller so that it now accepts GET requests to the resource/blueprints/{author}/{bpname}, which returns using a JSON representation only ONE plane, in this case the one made by {author} and whose name is {bpname}. Again, if there is no such author, you must respond with the HTTP 404 error code.

    ![](/BLUEPRINTS-PART2/img/autor-name1.jpg)

    ![](/BLUEPRINTS-PART2/img/autor-name1.jpg)


###  PART II

- Add the handling of POST requests (creation of new plans), so that an http client can register a new order by making a POST request to the resource planes, and sending as content of the request all the detail of said resource through a JSON document. For this, consider the following example, which considers - by consistency with the HTTP protocol - the handling of HTTP status codes (in case of success or error):

    ![](/BLUEPRINTS-PART2/img/Post.jpg)

- To test that the planes resource correctly accepts and interprets POST requests, use the Unix curl command. This command has as a parameter the type of content handled (in this case JSON), and the message body that will go with the request, which in this case must be a JSON document equivalent to the Client class (where instead of {JSON Object}, a JSON object corresponding to a new order will be used.

    - Una vez creamos el comando usando cURL, procedmos a ejecutarlo teniendo en cuenta que los datos deben ser correctos para que funcione y el POST se realice de manera correcta o de lo contrario tendremos un error.
    
    ![](/BLUEPRINTS-PART2/img/Insertar.jpg)
    
    - Luego de que la operacion se realizó exitosamente (Http 202) verificamos antes de refrescar la pagina que este corriendo la aplicación en nuestra maquina local.
    
    ![](/BLUEPRINTS-PART2/img/antesDelPost.jpg)
    
    - Y finalmente refrescamos para verificar que el post efectivamente se realizo de manera correcta.
    
    ![](/BLUEPRINTS-PART2/img/luegoDelPost.jpg)
    
    - Creación del método PUT en el Controller:
    
    ![](/BLUEPRINTS-PART2/img/put.jpg)

	Delegación hasta InMemoryBlueprintPersistence:

	![](/BLUEPRINTS-PART2/img/put1.jpg)

	Antes de ejecutar el comando:

	![](/BLUEPRINTS-PART2/img/put2.jpg)

	Ejecutando el comadno:

	![](/BLUEPRINTS-PART2/img/put3.jpg)

	Refrescando la página:

	![](/BLUEPRINTS-PART2/img/put4.jpg)

###  PART III

- The BlueprintsRESTAPI component will work in a concurrent environment. That is, it will attend multiple requests simultaneously (with the stack of applications used, these requests will be attended by default across multiple threads). Given the above, you should review your API (once it works), and identify:

    - What race conditions could occur?
    
    Mientras la página esté recibiendo una petición GET, se puede estar realizando una solicutud PUT o POST que modifique los valores y genere datos corruptos para un lado o para el otro.  

    - What are the respective critical regions?
    
    Las peticiones GET, POST y PUT hacen uso respectivamente de estos tres métodos, por esto es que se considera como la región crítica.

    ![](/BLUEPRINTS-PART2/img/carrera.jpg)
    
- Set the code to suppress race conditions. Keep in mind that simply synchronizing access to persistence/query operations will significantly degrade the API performance, so you should look for alternative strategies.
Write your analysis and the solution applied to the file README.txt
    
    Como las peticiones GET, PUT y POST hacen uso de los tres metodos previamente mencionados en la region critica, es importante poder permitir el acceso concurrente a los datos y evitar que los mismos sean inconsistenes, por esta razon 
    se hace uso de un hashmap concurrente el cual permite las mismas operaciones que un hashmap normal pero adicionalmente permite el acceso concurrente mencionado.
    
    ![](/BLUEPRINTS-PART2/img/hash.jpg)

