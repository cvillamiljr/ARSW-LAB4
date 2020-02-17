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

    ![](/BLUEPRINTS-PART2/img/autor-name.jpg)

    ![](/BLUEPRINTS-PART2/img/autor-name1.jpg)
