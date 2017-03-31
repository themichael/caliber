package caliber;

import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Fire up that ApplicationContext and get them mappings 
 * @author Patrick Walsh
 *
 */
public class AppCtxt {

	public static void main(String[] args) {
		new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/beans.xml");
	}
	
}
