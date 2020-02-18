package edu.eci.arsw.blueprints.test.services;

import static org.junit.Assert.assertEquals;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.impl.InMemoryBlueprintPersistence;
import edu.eci.arsw.blueprints.persistence.impl.Tuple;
import edu.eci.arsw.blueprints.services.BlueprintsServices;

public class ApplicationServicesTests {

    
    @Test
    public void contextLoads() {
        
    	InMemoryBlueprintPersistence BP = new InMemoryBlueprintPersistence();
    	String author = "diego";
    	String name = "pintura";
    	
    	Point[] pts2=new Point[]{new Point(362, 84),new Point(115, 115)};
        Blueprint bp2=new Blueprint("diego", "pintura",pts2);
        
        BP.setBluePrint("diego", "pintura", bp2);
    	
        
        //nuevo punto:
    	Point[] pts=new Point[]{new Point(140, 10)};
         
        Blueprint bp=new Blueprint(author, name, pts);
    	
        //probar el método setBluePrint
    	BP.setBluePrint(author, name, bp);
    	
    	try {
    		System.out.println(BP.getBlueprint(author, name).getAuthor());
    		assertEquals(BP.getBlueprint(author, name).getPoints().get(0).getX(),140);
    		assertEquals(BP.getBlueprint(author, name).getPoints().get(0).getY(),10);
		} catch (BlueprintNotFoundException e) {
			e.printStackTrace();
		}
    	
    }

}
