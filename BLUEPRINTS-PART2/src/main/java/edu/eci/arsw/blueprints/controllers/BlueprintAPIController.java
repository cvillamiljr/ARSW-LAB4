/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.controllers;

import java.util.LinkedHashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;

/**
 *
 * @author hcadavid
 */
@RestController
@RequestMapping(value = "/blueprints")
public class BlueprintAPIController {
    
    @Autowired
    @Qualifier("BlueprintsServices")
    BlueprintsServices BS = null;

    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> manejadorGetRecursoBlueprintAPI(){
	    try {
	        //obtener datos que se enviarán a través del API
	    	BS.filtrar();
	        Set<Blueprint> data = BS.getAllBlueprints();
	        return new ResponseEntity<>(data,HttpStatus.ACCEPTED);
	    } catch (BlueprintNotFoundException ex) {
	        return new ResponseEntity<>("Error bla bla bla",HttpStatus.NOT_FOUND);
	    }  
	    
    }
    
    @RequestMapping(value="/{author}")
    public ResponseEntity<?> getByAuthor(@PathVariable String author){
    	try {
			return new ResponseEntity<>(BS.getBlueprintsByAuthor(author),HttpStatus.ACCEPTED);
		} catch (BlueprintNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}
    }
    
    @RequestMapping(value="/{author}/{name}")
    public ResponseEntity<?> getByAuthorAndName(@PathVariable String author,@PathVariable String name){
    	try {
			return new ResponseEntity<>(BS.getBlueprint(author, name),HttpStatus.ACCEPTED);
		} catch (BlueprintNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<?> registro(@RequestBody Blueprint b){
    	try {
    		System.out.println(b.toString());
			BS.addNewBlueprint(b);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (BlueprintPersistenceException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
    }
    
    @RequestMapping(method=RequestMethod.PUT)
    public ResponseEntity<?> actualizar(@PathVariable String author,@PathVariable String name, @RequestBody Blueprint b ) {
    	try {
    		Blueprint bs= BS.getBlueprint(author, name);
    		System.out.println(b.getPoints());
    		bs.setPoints(b.getPoints());
    		
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (BlueprintNotFoundException e1) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    }
}

